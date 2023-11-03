package com.education.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Класс AddressDto, dto для класса Address.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressDto {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Полный адрес")
    private String fullAddress;

    @Schema(description = "Улица")
    private String street;

    @Schema(description = "Номер дома")
    private String house;

    @Schema(description = "Индекс")
    private String index;

    @Schema(description = "Корпус")
    private String housing;

    @Schema(description = "Строение")
    private String building;

    @Schema(description = "Город")
    private String city;

    @Schema(description = "Регион")
    private String region;

    @Schema(description = "Страна")
    private String country;

    @Schema(description = "Этаж")
    private String flat;

}
