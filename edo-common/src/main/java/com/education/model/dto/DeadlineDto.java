package com.education.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Schema(description = "Класс DeadlineDto - DTO для Deadline.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class DeadlineDto {

    @Schema(description = "id дедлайна")
    private Long id;

    @Schema(description = "Дата дедлайна")
    private ZonedDateTime date;

    @Schema(description = "Причина переноса")
    private String comment;

    @Schema(description = "Резолюция, к которой относится дедлайн",hidden = true)
    private ResolutionDto resolution;

}
