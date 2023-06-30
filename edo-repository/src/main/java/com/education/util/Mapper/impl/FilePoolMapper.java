package com.education.util.Mapper.impl;


import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilePoolMapper extends Mappable<FilePool, FilePoolDto> {
    FilePoolDto toDto(FilePool filePool);
}
