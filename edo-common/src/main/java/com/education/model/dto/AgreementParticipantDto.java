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

    private Long id;
    private String participantType;
    private ZonedDateTime creationDate;
    private ZonedDateTime dueDate;
    private ZonedDateTime receiptDate;
    private ZonedDateTime completionDate;
    private int approvalOrder;
    private int displayOrder;
}
