package com.education.util.Mapper.impl;

import com.education.entity.Author;
import com.education.model.dto.AuthorDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends Mappable<Author, AuthorDto> {
}
