package com.education.model.dto;

import com.education.model.enumEntity.EnumResolution;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.time.ZonedDateTime;
import java.util.List;

@ApiModel("DTO для POJO класса Resolution")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResolutionDto {

    @ApiModelProperty("Id резолюции")
    private Long id;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @ApiModelProperty("Дата создания резолюции")
    private ZonedDateTime creationDate;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @ApiModelProperty("Дата создания резолюции")
    private ZonedDateTime archivedDate;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @ApiModelProperty("Дата последнего события")
    private ZonedDateTime lastActionDate;

    @ApiModelProperty("Переменная отражающая тип решения")
    private EnumResolution enumResolution;

    @ApiModelProperty("Работник создавший резолюцию")
    private EmployeeDto creator;

    @ApiModelProperty("Работник подписывающий документ")
    private EmployeeDto signer;

    @ApiModelProperty("Работники выполняющие решение")
    private List<EmployeeDto> executors;

    @ApiModelProperty("Работник курирующий работу")
    private EmployeeDto curator;

    @ApiModelProperty("Вопрос, к которому относится резолюция")
    private QuestionDto question;

}
