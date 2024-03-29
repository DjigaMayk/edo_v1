package com.education.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Schema(description = "Класс AgreementParticipantDto, dto для класса AgreementParticipant.class")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AgreementParticipantDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Тип участника")
    private String participantType;

    @Schema(description = "Дата создания")
    private ZonedDateTime creationDate;

    @Schema(description = "Срок выполнения")
    private ZonedDateTime dueDate;

    @Schema(description = "Дата получения")
    private ZonedDateTime receiptDate;

    @Schema(description = "Дата завершения")
    private ZonedDateTime completionDate;

    @Schema(description = "Номер по порядку согласования")
    private int approvalOrder;
}
