package com.education.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(description = "Класс FederalDistrictDto, dto для класса FederalDistrict.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FederalDistrictDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Название федерального округа")
    private String federalDistrictName;

    @Schema(description = "Сайт федерального округа")
    private String website;

}
