package com.education.feign.feign_appeal.service.impl;

import com.education.feign.feign_appeal.service.AppealFeignClient;
import com.education.feign.feign_appeal.service.CreatingAppealFeignService;
import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.model.enumEntity.EnumAppealStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CreatingAppealFeignServiceImpl implements CreatingAppealFeignService {

    private final AppealFeignClient appealFeignClient;

    @Override
    public AppealDto createAppeal(AppealDto appealDto) {
        appealDto.setAppealStatus(EnumAppealStatus.NEW);
        return appealFeignClient.createAppeal(appealDto);
    }

    @Override
    public List<AppealAbbreviatedDto> findAllByIdEmployee(Long startIndex, Long amount) {
        return appealFeignClient.findAllByIdEmployee(startIndex, amount);
    }

    @Override
    public AppealDto findById(Long id) {
        return appealFeignClient.findById(id);
    }
}
