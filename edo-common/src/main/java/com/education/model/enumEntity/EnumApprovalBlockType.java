package com.education.model.enumEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Тип блока согласования (ApprovalBlock)
 * @author Denis Maslov
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EnumApprovalBlockType {

        PARALLEL("Параллельный"),
        SERIAL("Последовательный");

        private final String appealStatus;
}
