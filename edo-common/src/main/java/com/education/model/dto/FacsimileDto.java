package com.education.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Никита Бадеев
 */

@Schema(description = "DTO для Facsimile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacsimileDto {

    @Schema(description = "Id Факсимиле")
    private Long id;

    @Schema(description = "Связь с работником")
    private EmployeeDto employee;

    @Schema(description = "Связь с департаментом")
    private DepartmentDto department;

    @Schema(description = "Связь с файлом")
    private FilePoolDto file;

    @Schema(description = "Архивирован ли факсимиле")
    private boolean isArchived;

}
