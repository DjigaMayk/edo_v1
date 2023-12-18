package com.education.service.Resolution;

import com.education.model.dto.ResolutionDto;

import java.util.List;

public interface ResolutionService {

    ResolutionDto save(ResolutionDto resolutionDto);

    ResolutionDto findById(Long id);

    List<ResolutionDto> findAllByAppealIdNotArchived(Long appealId);
}
