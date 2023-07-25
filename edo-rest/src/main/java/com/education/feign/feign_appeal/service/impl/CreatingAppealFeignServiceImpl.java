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
    private final EnumAppealStatus STATUS_FOR_NEW_APPEAL = EnumAppealStatus.NEW;

    @Override
    public AppealDto createAppeal(AppealDto appealDto) {
        appealDto.setAppealStatus(STATUS_FOR_NEW_APPEAL);
        return appealFeignClient.createAppeal(appealDto);
    }

    @Override
    public List<AppealAbbreviatedDto> findAllByIdEmployee(Long first, Long amount) {
        return appealFeignClient.findAllByIdEmployee(first, amount);
    }

    @Override
    public AppealDto findById(Long id) {
        return appealFeignClient.findById(id);
    }
}
