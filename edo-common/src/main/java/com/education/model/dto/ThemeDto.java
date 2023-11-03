package com.education.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "Класс ThemeDto, DTO для класса Theme.class")
public class ThemeDto {

    @Schema(description = "id темы обращения")
    private Long id;

    @Schema(description = "Название темы обращения")
    private String name;

    @Schema(description = "Дата создания темы")
    private ZonedDateTime creationDate;

    @Schema(description = "Дата архивации темы")
    private ZonedDateTime archivedDate;

    @Schema(description = "Код темы")
    private String code;

    @Schema(description = "Родительская тема",hidden = true)
    private ThemeDto parentThemeDto;

}