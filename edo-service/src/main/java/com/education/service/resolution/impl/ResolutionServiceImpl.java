package com.education.service.resolution.impl;

import com.education.feign.feign_resolution.ResolutionFeignService;
import com.education.model.dto.ResolutionDto;
import com.education.service.appeal.AppealService;
import com.education.model.enumEntity.EnumAppealStatus;
import com.education.service.resolution.ResolutionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    private final ResolutionFeignService resolutionFeignService;
    private final AppealService appealService;

    /**
     * Метод для сохранения резолюции
     * Если явно не указали, что резолюция является черновиком, то устанавливаем полю isDraft значение true.
     * Если резолюция не является черновиком, то статус обращения менятся на UNDER_CONSIDERATION("На рассмотрении").
     *
     * @param resolutionDto
     * @return
     */
    @Override
    public ResolutionDto save(ResolutionDto resolutionDto) {
        if (resolutionDto.getIsDraft() == null) {
            resolutionDto.setIsDraft(true);
        }
        var resolutionAfter = resolutionFeignService.save(resolutionDto);
        if (BooleanUtils.isNotTrue(resolutionDto.getIsDraft())) {
            appealService.moveToUnderConsideration(resolutionAfter.getId());
        }
        return resolutionAfter;
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
        if (CollectionUtils.isEmpty(findAllByAppealIdNotArchived(appeal.getId()))) {
            String appealStatus = appeal.getRegistrationDate() == null
                    ? EnumAppealStatus.NEW.toString()
                    : EnumAppealStatus.REGISTERED.toString();
            appealService.moveToNewOrRegistered(appeal.getId(), appealStatus);
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
