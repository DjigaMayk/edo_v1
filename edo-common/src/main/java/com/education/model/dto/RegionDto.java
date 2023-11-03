package com.education.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.ZonedDateTime;

@Schema(description = "Класс RegionDto, dto для класса Region.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegionDto {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Идентификатор региона из внешних систем")
    private String externalId;

    @Schema(description = "Название региона")
    private String regionName;

    @Schema(description = "Дата архивации")
    private ZonedDateTime archivedDate;

    @Schema(description = "Количество")
    private String quantity;

    @Schema(description = "ID федерального округа")
    private FederalDistrictDto federalDistrict;

    @Schema(description = "Количество первичных отделений в регионе")
    private String numberOfPrimaryBranches;

    @Schema(description = "Количество местных отделений в регионе")
    private String numberOfLocalBranches;

}
