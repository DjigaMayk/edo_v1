package com.education.util.Mapper.impl;

import com.education.entity.Report;
import com.education.model.dto.ReportDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Alexey Pshenichny
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, ResolutionMapper.class})
public interface ReportMapper extends Mappable<Report, ReportDto> {

    @Override
    @Mapping(target = "creator", qualifiedByName = "ignoreEmployeeNotification")
    ReportDto toDto(Report report);

}
