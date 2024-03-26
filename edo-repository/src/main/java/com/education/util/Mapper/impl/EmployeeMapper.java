package com.education.util.Mapper.impl;

import com.education.entity.Employee;
import com.education.model.dto.EmployeeDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = NotificationMapper.class)
public interface EmployeeMapper extends Mappable<Employee, EmployeeDto> {
    @Override
    @Mapping(target = "notification", ignore = true)
    EmployeeDto toDto(Employee employee);

    @Override
    @Mapping(target = "notification", ignore = true)
    Employee toEntity(EmployeeDto employeeDto);
}
