package com.education.util.Mapper.impl;

import com.education.entity.Department;
import com.education.model.dto.DepartmentDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, AddressMapper.class})
public interface DepartmentMapper extends Mappable<Department, DepartmentDto> {
    @Override
    @Mapping(target = "department.department", ignore = true)
    DepartmentDto toDto(Department department);

    @Override
    @Mapping(target = "department.department", ignore = true)
    Department toEntity(DepartmentDto departmentDto);
}
