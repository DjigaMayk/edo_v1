package com.education.util.Mapper.impl;

import com.education.entity.AgreementParticipant;
import com.education.model.dto.AgreementParticipantDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgreementParticipantMapper extends Mappable<AgreementParticipant, AgreementParticipantDto> {
}
