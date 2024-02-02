package com.education.util.Mapper.impl;

import com.education.entity.Deadline;
import com.education.entity.Resolution;
import com.education.model.dto.DeadlineDto;
import com.education.model.dto.ResolutionDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DeadlineMapper extends Mappable<Deadline, DeadlineDto> {

    @Override
    @Mapping(target = "resolution", qualifiedByName = "onlyResolutionId")
    DeadlineDto toDto(Deadline deadline);

    @Named("onlyResolutionId")
    default ResolutionDto onlyResolutionId(Resolution resolution) {
        if (resolution != null) {
            var resolutionDto = new ResolutionDto();
            resolutionDto.setId(resolution.getId());
            return resolutionDto;
        }
        return null;
    }
}
