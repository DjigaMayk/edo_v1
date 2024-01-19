package com.education.model.dto;

import com.education.model.enumEntity.EnumApprovalBlockType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author Denis Maslov
 */
@Schema(description = "Класс ApprovalBlockDto, dto для класса ApprovalBlock.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApprovalBlockDto {

    @Schema(description = "Лист согласования в блоке согласования")
    private ApprovalSheetDto approvalSheet;

    @Schema(description = "Тип блока согласования")
    private EnumApprovalBlockType approvalBlockType;
}
