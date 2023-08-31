package com.education.service.resolution.impl;

import com.education.entity.Resolution;
import com.education.repository.ResolutionRepository;
import com.education.service.appeal.AppealService;
import com.education.service.resolution.ResolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    final ResolutionRepository resolutionRepository;
    final private AppealService appealService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Resolution save(Resolution resolution) {
        // Если резолюция не является черновиком, то меняем статус обращения на UNDER_CONSIDERATION("На рассмотрении")
        var resolutionAfter = resolutionRepository.save(resolution);
        if (!resolutionAfter.getIsDraft()) {
            appealService.moveToUnderConsideration(resolutionAfter.getId());
        }
        return resolutionAfter;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        resolutionRepository.moveToArchive(id);
    }


    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Resolution findById(Long id) {
        return resolutionRepository.findById(id).get();
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Resolution> findAllById(Iterable<Long> ids) {
        return resolutionRepository.findAllById(ids);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Resolution findByIdNotArchived(Long id) {
        return resolutionRepository.findByIdNotArchived(id).orElse(null);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Resolution> findAllByIdNotArchived(Iterable<Long> ids) {
        return resolutionRepository.findAllByIdNotArchived(ids);

    }
}
