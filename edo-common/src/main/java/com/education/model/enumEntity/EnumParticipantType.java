package com.education.model.enumEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EnumParticipantType {

    INITIATOR("Инициатор"),

    PARTICIPANT("Участник"),

    SIGNATORY("Подписант");

    private final String participantType;
}
