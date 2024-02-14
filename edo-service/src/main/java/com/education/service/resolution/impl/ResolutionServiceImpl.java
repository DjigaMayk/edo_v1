package com.education.service.resolution.impl;

import com.education.feign.feign_resolution.ResolutionFeignService;
import com.education.model.dto.ResolutionDto;
import com.education.service.AbstractService;
import com.education.service.appeal.AppealService;
import com.education.model.enumEntity.EnumAppealStatus;
import com.education.service.resolution.ResolutionService;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.education.utils.sorterResolutions.SorterResolutions.sortResolutions;

@Service
public class ResolutionServiceImpl extends AbstractService<ResolutionFeignService, ResolutionDto> implements ResolutionService {

    private final ResolutionFeignService resolutionFeignService;
    private final AppealService appealService;

    public ResolutionServiceImpl(ResolutionFeignService resolutionFeignService, ResolutionFeignService resolutionFeignService1, AppealService appealService) {
        super(resolutionFeignService);
        this.resolutionFeignService = resolutionFeignService1;
        this.appealService = appealService;
    }

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

    /**
     * Метод для удаления резолюции из архива.
     * После удаления резолюции из архива метод проверяет, есть ли у этого обращения еще незаархивированные резолюции.
     * Если разархивируемая резолюция единственная, и она не является черновиком, то статус обращения меняется на UNDER_CONSIDERATION.
     *
     * @param id идентификатор резолюции, удаляемой из архива
     */
    public void removeFromArchive(Long id) {
        resolutionFeignService.removeFromArchive(id);
        var appeal = appealService.findAppealByResolutionId(id);
        var resolutions = findAllByAppealIdNotArchived(appeal.getId());
        if (!CollectionUtils.isEmpty(resolutions) && resolutions.size() == 1
                && !resolutionFeignService.isDraft(id)) {
            appealService.moveToUnderConsideration(appeal.getId());
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
        List<ResolutionDto> list = new ArrayList<ResolutionDto>();
        list = resolutionFeignService.findAllByAppealIdNotArchived(appealId);
        list = sortResolutions(list);
        return list;

    }

    /**
     * Метод для выгрузки всех резолюций с возможностью фильтра по признаку архивности.
     *
     * @param filter фильтр указывающий какая выборка Резолюций запрошена:
     *               null или all - все резолюции
     *               nonarchived - без архивных
     *               archived - только архивные
     */
    @Override
    public List<ResolutionDto> findAllWithFilterArchived(@Nullable String filter) {
        return resolutionFeignService.findAllWithFilterArchived(filter);
    }
}
