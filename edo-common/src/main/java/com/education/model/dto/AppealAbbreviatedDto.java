package com.education.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Data
@Schema(description = "Класс AppealAbbreviatedDto, dto для класса appeal.class")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AppealAbbreviatedDto {

    @Schema(description = "Номер обращения")
    private String number;

    @Schema(description = "Дата создания обращения")
    private ZonedDateTime creationDate;

    @Schema(description = "Описание обращения")
    private String annotation;

    @Schema(description = "Автор")
    private EmployeeDto creator;

}
