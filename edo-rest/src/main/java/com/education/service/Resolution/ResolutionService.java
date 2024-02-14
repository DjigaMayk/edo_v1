package com.education.service.Resolution;

import com.education.model.dto.ResolutionDto;
import org.springframework.lang.Nullable;
import com.education.service.BaseInterface;

import java.util.List;

public interface ResolutionService extends BaseInterface<ResolutionDto> {

    ResolutionDto save(ResolutionDto resolutionDto);

    ResolutionDto findById(Long id);

    List<ResolutionDto> findAllByAppealIdNotArchived(Long appealId);

    List<ResolutionDto> findAllWithFilterArchived(@Nullable String filter);
}
