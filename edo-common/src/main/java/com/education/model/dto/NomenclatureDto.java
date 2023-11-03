package com.education.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Schema(description = "Класс NomenclatureDTO - DTO для Nomenclature.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NomenclatureDto {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "Дата создания номенклатуры")
    private ZonedDateTime creationDate;

    @Schema(description = "Дата перевода в архив")
    private ZonedDateTime archivedDate;

    @Schema(description = "Шаблон")
    private String template;

    @Schema(description = "Текущее значение")
    private Long currentValue;

    @Schema(description = "Индекс")
    private String index;

}
