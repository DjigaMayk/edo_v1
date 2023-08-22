package com.education.util.Mapper.impl;

import com.education.entity.Facsimile;
import com.education.model.dto.FacsimileDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, EmployeeMapper.class, FilePoolMapper.class})
public interface FacsimileMapper extends Mappable<Facsimile, FacsimileDto> {
    @Override
    @Mapping(target = "department.department", ignore = true)
    FacsimileDto toDto(Facsimile facsimile);
}
