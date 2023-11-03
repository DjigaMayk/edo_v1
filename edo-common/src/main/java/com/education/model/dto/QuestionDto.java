package com.education.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Schema(description = "Класс QuestionDto - DTO для Question.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class QuestionDto {

    @Schema(description = "Id обращения")
    private Long id;

    @Schema(description = "Дата создания обращения")
    private ZonedDateTime creationDate;

    @Schema(description = "Дата архивирования обращения")
    private ZonedDateTime archivedDate;

    @Schema(description = "Краткое содержание обращения")
    private String summary;

    @Schema(description = "Список резолюций по вопросу")
    @JsonInclude(NON_NULL)
    private List<ResolutionDto> resolutions;

    @Schema(description = "Тема, к которой относится вопрос")
    private ThemeDto theme;

}
