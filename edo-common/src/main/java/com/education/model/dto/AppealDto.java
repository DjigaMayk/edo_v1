package com.education.model.dto;

import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.enumEntity.EnumWayToAnswer;
import com.education.model.enumEntity.EnumWayToReceive;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;


@Data
@Schema(description = "Класс AppealDTO, dto для класса appeal.class")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AppealDto {

    @Schema(description = "Id обращения")
    private Long id;

    @Schema(description = "Дата создания обращения")
    private ZonedDateTime creationDate;

    @Schema(description = "Дата архивирования обращения")
    private ZonedDateTime archivedDate;

    @Schema(description = "Дата регистрации обращения")
    private ZonedDateTime registrationDate;

    @Schema(description = "Номер обращения")
    private String number;

    @Schema(description = "Описание обращения")
    private String annotation;

    @Schema(description = "Подписи")
    private List<EmployeeDto> signer;

    @Schema(description = "Автор")
    private EmployeeDto creator;

    @Schema(description = "Получатели")
    private List<EmployeeDto> addressee;

    @Schema(description = "Номенклатура")
    private NomenclatureDto nomenclature;

    @Schema(description = "Авторы обращения")
    private List<AuthorDto> authors;

    @Schema(description = "Файлы, связанные с обращением")
    private List<FilePoolDto> file;

    @Schema(description = "Вопросы, связанные с обращением")
    private List<QuestionDto> question;

    @Schema(description = "Статус обращения")
    private EnumAppealStatus appealStatus;

    @Schema(description = "Способ получения обращения")
    private EnumWayToReceive sendingMethod;

    @Schema(description = "Способ получения ответа на обращение")
    private EnumWayToAnswer answeringMethod;

    @Schema(description = "ID региона, связанного с обращением")
    private RegionDto region;

    @Schema(description = "Флаг отправки письма. Если true, то письмо было отправлено")
    private boolean isMailSent;
}