package com.education.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * @author Alexey Pshenichny
 */
@Schema(description = "DTO для класса Report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReportDto {

    @Schema(description = "Id отчёта")
    private Long id;

    @Schema(description = "Дата создания отчёта")
    private ZonedDateTime creationDate;

    @Schema(description = "Работник, создавший отчёт")
    private EmployeeDto creator;

    @Schema(description = "Задание, к которому относится отчёт")
    private ResolutionDto resolution;

    @Schema(description = "Комментарий к заданию")
    private String comment;

    @Schema(description = "Флаг, показывающий, было ли выполнено задание")
    private Boolean isResolutionCompleted;

}
