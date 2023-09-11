package com.education.service.appeal.impl;

import com.education.author_feign.service.AuthorService;
import com.education.feign.feign_appeal.AppealFeignService;
import com.education.model.constant.RabbitConstant;
import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.model.dto.QuestionDto;
import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.records.AppealReadRecord;
import com.education.model.util.exceptions.AppealNotValidException;
import com.education.service.appeal.AppealService;
import com.education.service.nomenclature.NomenclatureFeignService;
import com.education.service.question.QuestionService;
import com.education.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.util.StringUtils.hasLength;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppealServiceImpl implements AppealService {

    private final QuestionService questionService;
    private final AuthorService authorService;
    private final RegionService regionService;
    private final AmqpTemplate amqpTemplate;
    private final AppealFeignService appealFeignService;
    private final NomenclatureFeignService nomenclatureService;

    @Override
    public AppealDto save(AppealDto appealDto) {
        String validateResult = validateAppealDto(appealDto);
        if (!validateResult.isEmpty()) {
            throw new AppealNotValidException(validateResult);
        }
        var savedAuthors = appealDto.getAuthors()
                .stream().map(authorService::save)
                .map(ResponseEntity::getBody)
                .toList();
        var savedQuestions = appealDto.getQuestion()
                .stream().map(questionService::save).toList();
        var savedRegions = appealDto.getRegion();
        regionService.save(savedRegions);
        appealDto.setRegion(savedRegions);
        appealDto.setAuthors(savedAuthors);
        appealDto.setQuestion(savedQuestions);
        final String NUMBER = nomenclatureService
                .getNumberFromTemplate(appealDto.getNomenclature());
        appealDto.setNumber(NUMBER);

        return appealFeignService.save(appealDto);
    }

    @Override
    public void moveToArchive(Long id) {
        appealFeignService.moveToArchive(id);
    }

    @Override
    public AppealDto findById(Long id) {
        AppealDto response = appealFeignService.findById(id);
        if (response != null && response.getAppealStatus().equals(EnumAppealStatus.NEW)) {
            amqpTemplate.convertAndSend(RabbitConstant.EXCHANGE, RabbitConstant.ADDRESS_APPEAL_IS_READ,
                    new AppealReadRecord(id, new Date()));
        }
        return response;
    }

    @Override
    public List<AppealDto> findAllById(Iterable<Long> ids) {
        return appealFeignService.findAllById(ids);
    }

    @Override
    public AppealDto findByIdNotArchived(Long id) {
        return appealFeignService.findByIdNotArchived(id);
    }

    @Override
    public List<AppealDto> findAllByIdNotArchived(Iterable<Long> ids) {
        return appealFeignService.findAllByIdNotArchived(ids);
    }

    @Override
    public List<AppealAbbreviatedDto> findAllByIdEmployee(Long startIndex, Long amount) {
        return appealFeignService.findAllByIdEmployee(startIndex, amount);
    }

    @Override
    public AppealDto findByQuestion(QuestionDto questionDto) {
        return appealFeignService.findByQuestionId(questionDto.getId());
    }

    @Override
    public AppealDto findAppealByResolutionId(Long resolutionId) {
        return appealFeignService.findAppealByResolutionId(resolutionId);
    }

    @Override
    public void moveToNewOrRegistered(Long id, String appealStatus) {
        appealFeignService.moveToNewOrRegistered(id, appealStatus);
    }

    @Override
    public void moveToUnderConsideration(Long id) {
        appealFeignService.moveToUnderConsideration(id);
    }

    private String validateAppealDto(AppealDto appealDto) {

        final String emailRegEx = "\\w+@\\w+\\.\\w+";
        final String phoneRegEx = "7\\d{10}";

        StringBuilder stringBuilder = new StringBuilder();
        Consumer<String> addIssue = (s) -> stringBuilder.append(s).append(";");
        var questions = appealDto.getQuestion();
        if (isEmpty(questions)) {
            addIssue.accept("field question is empty");
        } else {
            for (var question : questions) {
                if (!hasLength(question.getSummary())) {
                    if (question.getId() != null) {
                        addIssue.accept(String.format("question id: %d  field summary is empty or NULL", question.getId()));
                    }
                } else if (question.getSummary().length() > 200) {

                    addIssue.accept(String.format("question id: %d field Summary has more then 200 characters",
                            question.getId()));
                }
                if (question.getTheme() == null) {
                    addIssue.accept(String.format("question id: %d theme is empty", question.getId()));
                }
            }
        }
        var authors = appealDto.getAuthors();
        if (isEmpty(authors)) {
            addIssue.accept("this appeal has no authors");
        } else {
            for (var author : authors) {
                var authorEmail = author.getEmail();
                if (authorEmail == null) {
                    addIssue.accept(String.format("author with id: %d field email is NULL", author.getId()));
                } else if (!authorEmail.matches(emailRegEx)) {
                    addIssue.accept(String.format("author with id: %d has email, but incorrect format", author.getId()));
                }
                var authorMobilePhone = author.getMobilePhone();
                if (!hasLength(authorMobilePhone)) {
                    addIssue.accept(String.format("author with id: %d has email is NULL", author.getId()));
                } else if (!authorMobilePhone.matches(phoneRegEx)) {
                    addIssue.accept(String.format("author with id: %d has mobilePhone, but incorrect format",
                            author.getId()));
                }
                var authorFirstName = author.getFirstName();
                if (!hasLength(authorFirstName)) {
                    addIssue.accept(String.format("author with id: %d has first name either empty or NULL",
                            author.getId()));
                } else if (authorFirstName.length() > 60) {
                    addIssue.accept(String.format("author with id: %d has first name length more 60 characters",
                            author.getId()));
                }
                var authorLastName = author.getLastName();
                if (!hasLength(authorLastName)) {
                    addIssue.accept(String.format("author with id: %d has author last name either empty or NULL",
                            author.getId()));
                } else if (authorLastName.length() > 60) {
                    addIssue.accept(String.format("author with id: %d has last name length more 60 characters",
                            author.getId()));
                }
            }
        }
        if (appealDto.getSendingMethod() == null) {
            addIssue.accept("appeal has no sending method");
        }
        if (!stringBuilder.isEmpty()) {
            stringBuilder.insert(0, "Appeal not valid;");
        }
        return stringBuilder.toString();
    }
}
























