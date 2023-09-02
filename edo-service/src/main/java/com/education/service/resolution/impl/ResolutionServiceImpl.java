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

    @Override
    public ResolutionDto save(ResolutionDto resolution) {
        return resolutionFeignService.save(resolution);
    }

    /**
     * После перемещения резолюции в архив метод проверяет, остались ли у этого обращения незаархивированные резолюции.
     * Если нет, то в зависимости от наличия поля registrationDate у Appeal, выставляется
     * соответствующий статус обращения.
     *
     * @param id идентификатор резолюции, отправляемой в архив
     */
    @Override
    public void moveToArchive(Long id) {
        resolutionFeignService.moveToArchive(id);
        var appeal = appealService.findAppealByResolutionId(id);
        if (findAllByAppealIdNotArchived(appeal.getId()).isEmpty()) {
            if (appeal.getRegistrationDate() != null) {
                appealService.moveToRegistered(appeal.getId());
            } else {
                appealService.moveToNew(appeal.getId());
            }
        }
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

    @Override
    public List<ResolutionDto> findAllByAppealIdNotArchived(Long appealId) {
        return resolutionFeignService.findAllByAppealIdNotArchived(appealId);
    }
}
