package com.education.service.resolution.impl;

import com.education.feign.feign_resolution.ResolutionFeignService;
import com.education.model.dto.ResolutionDto;
import com.education.service.appeal.AppealService;
import com.education.service.resolution.ResolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    private final ResolutionFeignService resolutionFeignService;
    private final AppealService appealService;

    /**
     * Метод для сохранения резолюции
     * Если резолюция не является черновиком, то статус обращения менятся на UNDER_CONSIDERATION("На рассмотрении").
     *
     * @param resolutionDto
     * @return
     */
    @Override
    public ResolutionDto save(ResolutionDto resolutionDto) {
        var resolutionAfter = resolutionFeignService.save(resolutionDto);
        if (!resolutionAfter.getIsDraft()) {
            appealService.moveToUnderConsideration(resolutionAfter.getId());
        }
        return resolutionAfter;
    }

    @Override
    public void moveToArchive(Long id) {
        resolutionFeignService.moveToArchive(id);
    }

    @Override
    public ResolutionDto findById(Long id) {
        return resolutionFeignService.findById(id);
    }

    @Override
    public List<ResolutionDto> findAllById(Iterable<Long> ids) {
        return resolutionFeignService.findAllById(ids);
    }

    @Override
    public ResolutionDto findByIdNotArchived(Long id) {
        return resolutionFeignService.findByIdNotArchived(id);
    }

    @Override
    public List<ResolutionDto> findAllByIdNotArchived(Iterable<Long> ids) {
        return resolutionFeignService.findAllByIdNotArchived(ids);
    }
}
