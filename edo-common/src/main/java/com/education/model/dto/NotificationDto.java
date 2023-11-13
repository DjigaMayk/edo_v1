package com.education.model.dto;

import com.education.model.enumEntity.EnumNotification;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Класс NotificationDto, dto для класса Notification.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NotificationDto {

    @Schema(description = "Id оповещения")
    private Long id;

    @Schema(description = "Id работника, для которого уведомление")
    private EmployeeDto employee;

    @Schema(description = "Тип оповещения")
    private EnumNotification enumNotification;

}
