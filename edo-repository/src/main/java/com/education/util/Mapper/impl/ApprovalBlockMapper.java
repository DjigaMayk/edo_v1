package com.education.util.Mapper.impl;

import com.education.entity.ApprovalBlock;
import com.education.model.dto.ApprovalBlockDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ApprovalSheetMapper.class})
public interface ApprovalBlockMapper extends Mappable<ApprovalBlock, ApprovalBlockDto> {
}
