package com.education.service.resolution.impl;

import com.education.entity.Resolution;
import com.education.repository.ResolutionRepository;
import com.education.service.resolution.ResolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    final ResolutionRepository resolutionRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Resolution save(Resolution resolution) {
        return resolutionRepository.save(resolution);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        resolutionRepository.moveToArchive(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeFromArchive(Long id) {
        resolutionRepository.removeFromArchive(id);
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

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Resolution> findAllByAppealIdNotArchived(Long appealId) {
        return resolutionRepository.findAllByAppealIdNotArchived(appealId);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Boolean isDraft(Long id) {
        return resolutionRepository.isDraft(id).orElse(true);
    }

    /**
     * Метод для выгрузки всех резолюций с возможностью фильтра по признаку архивности.
     *
     * @param filter фильтр указывающий какая выборка Резолюций запрошена:
     *               null или all - все резолюции
     *               nonarchived - без архивных
     *               archived - только архивные
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Resolution> findAllWithFilterArchived(@Nullable String filter) {
        if (filter == null || filter.equals("all")) {
            return resolutionRepository.findAllResolution();
        } else if (filter.equals("nonarchived")) {
            return resolutionRepository.findAllResolutionNonArchived();
        } else if (filter.equals("archived")) {
            return resolutionRepository.findAllResolutionArchived();
        } else return null;
    }
}
