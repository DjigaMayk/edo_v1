package com.education.service.appeal;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.service.BaseInterface;

import java.util.List;


public interface CreatingAppealService extends BaseInterface<AppealDto> {

    AppealDto createAppeal(AppealDto appealDto);

    AppealDto editAppeal(AppealDto appealDto);

    List<AppealAbbreviatedDto> findAllByIdEmployee(Long first, Long amount);

    AppealDto findById(Long id);
}
