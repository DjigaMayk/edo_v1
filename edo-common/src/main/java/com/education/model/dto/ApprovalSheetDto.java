package com.education.model.dto;

import com.education.model.enumEntity.EnumApprovalUrgency;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * @author Ivan Chursinov
 */

@Schema(description = "Класс ApprovalSheetDto, dto для класса ApprovalSheet.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApprovalSheetDto {

    @Schema(description = "ID листа согласования")
    private Long id;

    @Schema(description = "Срочность рассмотрения")
    private EnumApprovalUrgency approvalUrgency;

    @Schema(description = "Ссылка на родительский лист согласования", hidden = true)
    private ApprovalSheetDto parentApprovalSheetDto;

    @Schema(description = "Комментарий инициатора")
    private String initiatorComment;

    @Schema(description = "Дата создания листа согласования")
    private ZonedDateTime creationDate;

    @Schema(description = "Дата перевода направления на согласование")
    private ZonedDateTime referralDate;

    @Schema(description = "Дата подписания")
    private ZonedDateTime signingDate;

    @Schema(description = "Дата перевода в архив")
    private ZonedDateTime archivedDate;

    @Schema(description = "Номер листа согласования")
    private String number;

    @Schema(description = "Обращения к которому относиться данный Лист Согласования")
    private AppealDto appeal;

}
