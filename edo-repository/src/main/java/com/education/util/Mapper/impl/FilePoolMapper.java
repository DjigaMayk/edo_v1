package com.education.util.Mapper.impl;


import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EmployeeMapper.class)
public interface FilePoolMapper extends Mappable<FilePool, FilePoolDto> {

    @Override
    @Mapping(target = "creator", ignore = true)
    FilePoolDto toDto(FilePool filePool);

    @Override
    @Mapping(target = "creator", ignore = true)
    FilePool toEntity(FilePoolDto filePoolDto);
}
