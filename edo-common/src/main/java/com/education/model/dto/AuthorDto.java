package com.education.model.dto;

import com.education.model.enumEntity.EnumEmployment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Класс DTO для сущности Author(автор)")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthorDto {

    @Schema(description = "id автора")
    private Long id;

    @Schema(description = "Имя автора")
    private String firstName;

    @Schema(description = "Фамилия автора")
    private String lastName;

    @Schema(description = "Отчество автора")
    private String middleName;

    @Schema(description = "Адрес автора")
    private String address;

    @Schema(description = "СНИЛС автора")
    private String snils;

    @Schema(description = "Номер телефона автора")
    private String mobilePhone;

    @Schema(description = "Электронная почта автора")
    private String email;

    @Schema(description = "Рабочий статус автора")
    private EnumEmployment employment;

    @Schema(description = "ФИО автора в дательном падеже")
    private String fioDative;

    @Schema(description = "ФИО автора в родительном падеже")
    private String fioGenitive;

    @Schema(description = "ФИО автора в именительном падеже")
    private String fioNominative;

}
