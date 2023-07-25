package com.education.feign.feign_appeal.service;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;

import java.util.List;


public interface CreatingAppealFeignService {
    AppealDto createAppeal(AppealDto appealDto);

    List<AppealAbbreviatedDto> findAllByIdEmployee(Long first, Long amount);

    AppealDto findById(Long id);
}

