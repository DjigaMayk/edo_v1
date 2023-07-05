package com.education.service.appeal.impl;

import com.education.entity.Appeal;
import com.education.repository.AppealRepository;
import com.education.service.appeal.AppealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppealServiceImpl implements AppealService {

    final AppealRepository appealRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Appeal save(Appeal appeal) {
        appeal.setCreationDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return appealRepository.saveAndFlush(appeal);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        appealRepository.moveToArchive(id);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findById(Long id) {
        return appealRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllById(Iterable<Long> ids) {
        return appealRepository.findAllById(ids);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findAppealByQuestionId(Long questionId) {
        return appealRepository.findAppealByQuestionId(questionId);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Appeal findByIdNotArchived(Long id) {
        return appealRepository.findByIdNotArchived(id).orElse(null);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllByIdNotArchived(Iterable<Long> ids) {
        return appealRepository.findAllByIdNotArchived(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToUnderConsideration(Long resolutionId) {
        appealRepository.moveToUnderConsideration(resolutionId);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Appeal> findAllByIdEmployee(Long id, Long first, Long amount) {
        return appealRepository.findByIdEmployee(id, first, amount);
    }
}
