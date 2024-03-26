package com.education.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Степан Ритман
 */
@Schema(description = "DTO для класса Employee")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    @Schema(description = "Id работника")
    private Long id;

    @Schema(description = "Имя работника")
    private String firstName;

    @Schema(description = "Фамилия работника")
    private String lastName;

    @Schema(description = "Отчество работника")
    private String middleName;

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "URL-адрес фото")
    private String photoUrl;

    @Schema(description = "ФИО в дательном падеже")
    private String fioDative;

    @Schema(description = "ФИО в именительном падеже")
    private String fioNominative;

    @Schema(description = "ФИО в родительном падеже")
    private String fioGenitive;

    @Schema(description = "Внешний идентификатор")
    private String externalId;

    @Schema(description = "Мобильный номер телефона")
    private String phone;

    @Schema(description = "Рабочий номер телефона")
    private String workPhone;

    @Schema(description = "Рабочая почта")
    private String workEmail;

    @Schema(description = "Дата рождения")
    private ZonedDateTime birthDate;

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Дата создания")
    private ZonedDateTime creationDate;

    @Schema(description = "Дата архивирования")
    private ZonedDateTime archivedDate;

    @Schema(description = "Типы оповещения, которые выбрали пользователи")
    private List<NotificationDto> notification;

}
