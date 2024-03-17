package com.education.util.Mapper.impl;

import com.education.entity.Employee;
import com.education.entity.Report;
import com.education.entity.Resolution;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.ReportDto;
import com.education.model.dto.ResolutionDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.BeanUtils;

/**
 * @author Alexey Pshenichny
 */
@Mapper(componentModel = "spring")
public interface ReportMapper extends Mappable<Report, ReportDto> {

    @Override
    @Mapping(target = "creator", qualifiedByName = "employeeToDto")
    @Mapping(target = "resolution", qualifiedByName = "resolutionToDto")
    ReportDto toDto(Report report);

    @Named("resolutionToDto")
    default ResolutionDto resolutionToDto(Resolution resolution) {
        var resolutionDto = new ResolutionDto();
        if (resolution != null) {
            BeanUtils.copyProperties(resolution, resolutionDto, "reports");
        }
        return resolutionDto;
    }

    @Named("employeeToDto")
    default EmployeeDto employeeToDto(Employee employee) {
        var employeeDto = new EmployeeDto();
        if (employee != null) {
            BeanUtils.copyProperties(employee, employeeDto, "reports");
        }
        return employeeDto;
    }

}
