package com.education.util.Mapper.impl;

import com.education.entity.Department;
import com.education.model.dto.DepartmentDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends Mappable<Department, DepartmentDto> {
    /**
     * Method to avoid the loop in the Mapper
     */
    @Override
    @Mapping(target = "department.department", ignore = true)
    DepartmentDto toDto(Department department);
}
