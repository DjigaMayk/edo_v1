package com.education.service.appeal;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.model.dto.QuestionDto;

import java.util.List;

public interface AppealService {
    AppealDto save(AppealDto appeal);

    void moveToArchive(Long id);

    AppealDto findById(Long id);

    List<AppealDto> findAllById(Iterable<Long> ids);

    AppealDto findByIdNotArchived(Long id);

    List<AppealDto> findAllByIdNotArchived(Iterable<Long> ids);

    List<AppealAbbreviatedDto> findAllByIdEmployee(Long startIndex, Long amount);

    AppealDto findByQuestion(QuestionDto questionDto);

    AppealDto findAppealByResolutionId(Long resolutionId);

    void moveToRegistered(Long id);

    void moveToNew(Long id);
}