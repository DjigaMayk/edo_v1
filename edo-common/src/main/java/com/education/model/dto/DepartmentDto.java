package com.education.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Objects;

@Schema(description = "Dto для Department.class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {
    @Schema(description = "Department id")
    private Long id;

    @Schema(description = "Аббревиатура отдела")
    private String shortName;

    @Schema(description = "Название отдела полностью")
    private String fullName;

    @Schema(description = "Адрес отдела")
    private AddressDto address;

    @Schema(description = "Ключ внешней таблицы")
    private String externalId;

    @Schema(description = "Телефонный номер")
    private String phone;

    @Schema(description = "Вышестоящий отдел", hidden = true)
    private DepartmentDto department;

    @Schema(description = "Дата открытия")
    private ZonedDateTime creationDate;

    @Schema(description = "Дата закрытия")
    private ZonedDateTime archivedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDto that = (DepartmentDto) o;
        return externalId.equals(that.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(externalId);
    }

}
