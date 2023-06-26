package com.education.util.Mapper.impl;

import com.education.entity.Appeal;
import com.education.model.dto.AppealDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppealMapper extends Mappable<Appeal, AppealDto> {

    //создано для ухода от бесконечного цикла при маппинге
    @Override
    @Mapping(target = "question", ignore = true)
    AppealDto toDto(Appeal appeal);
}
