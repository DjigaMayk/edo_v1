package com.education.service.Resolution;

import com.education.feign.feign_resolution.ResolutionFeignClient;
import com.education.model.dto.ResolutionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    private final ResolutionFeignClient resolutionFeignClient;

    @Override
    public ResolutionDto save(ResolutionDto resolutionDto) {
        return resolutionFeignClient.save(resolutionDto);
    }

    @Override
    public  ResolutionDto findById(Long id) {return resolutionFeignClient.findById(id);}

    @Override
    public List<ResolutionDto> findAllByAppealIdNotArchived(Long appealId) {return resolutionFeignClient.findAllByAppealIdNotArchived(appealId); }

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
        return resolutionFeignClient.findAllWithFilterArchived(filter);
    }
}
