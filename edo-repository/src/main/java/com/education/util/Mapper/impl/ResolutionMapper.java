package com.education.util.Mapper.impl;

import com.education.entity.Employee;
import com.education.entity.Resolution;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.ResolutionDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ResolutionMapper extends Mappable<Resolution, ResolutionDto> {

    //создано для ухода от бесконечного цикла при маппинге
    @Override
    @Mapping(target = "question.resolutions", ignore = true)
    @Mapping(target = "signer", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "curator", ignore = true)
    @Mapping(target = "executors", qualifiedByName = "ignoreExecutors")
    ResolutionDto toDto(Resolution resolution);

    @Named("ignoreExecutors")
    default List<EmployeeDto> ignoreExecutors(List<Employee> executors) {
        return Collections.emptyList();
    }
}
