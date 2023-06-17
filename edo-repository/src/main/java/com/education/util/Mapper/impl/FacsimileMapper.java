package com.education.util.Mapper.impl;

import com.education.entity.Facsimile;
import com.education.model.dto.FacsimileDTO;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacsimileMapper extends Mappable<Facsimile, FacsimileDTO> {
}
