package com.education.model.dto;

import com.education.model.enumEntity.EnumResolution;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Schema(description = "DTO для POJO класса Resolution")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResolutionDto {

    @Schema(description = "Id резолюции")
    private Long id;

    @Schema(description = "Дата создания резолюции")
    private ZonedDateTime creationDate;

    @Schema(description = "Дата создания резолюции")
    private ZonedDateTime archivedDate;

    @Schema(description = "Дата последнего события")
    private ZonedDateTime lastActionDate;

    @Schema(description = "Переменная отражающая тип решения")
    private EnumResolution enumResolution;

    @Schema(description = "Работник создавший резолюцию")
    private EmployeeDto creator;

    @Schema(description = "Работник подписывающий документ")
    private EmployeeDto signer;

    @Schema(description = "Работники выполняющие решение")
    private List<EmployeeDto> executors;

    @Schema(description = "Работник курирующий работу")
    private EmployeeDto curator;

    @Schema(description = "Вопрос, к которому относится резолюция")
    private QuestionDto question;

    @Schema(description = "Список дедлайнов")
    private List<DeadlineDto> deadlines;

    @Schema(description = "Признак черновика резолюции")
    private Boolean isDraft;

}
