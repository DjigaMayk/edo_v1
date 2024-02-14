package com.education.service.resolution.impl;

import com.education.entity.BaseEntity;
import com.education.entity.Resolution;
import com.education.model.dto.ResolutionDto;
import com.education.repository.ResolutionRepository;
import com.education.service.AbstractService;
import com.education.service.resolution.ResolutionService;
import com.education.util.Mapper.impl.ResolutionMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ResolutionServiceImpl extends AbstractService<ResolutionRepository, Resolution, ResolutionDto, ResolutionMapper> implements ResolutionService {

    final ResolutionRepository resolutionRepository;

    public ResolutionServiceImpl(ResolutionRepository repository, ResolutionMapper resolutionMapper, ResolutionRepository resolutionRepository) {
        super(repository, resolutionMapper);
        this.resolutionRepository = resolutionRepository;
    }

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
        return Stream.ofNullable(resolutionRepository.findAllResolutionWithFilterArchived(filter))
                .map(r -> r.stream()
                        .map(BaseEntity::getId)
                        .toList())
                .map(resolutionRepository::findAllWithDeadline)
                .findAny()
                .orElse(new ArrayList<>());
    }
}
