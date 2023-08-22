package com.education.feign.feign_resolution.service;

import com.education.model.dto.ResolutionDto;

public interface ResolutionService {
    ResolutionDto save(ResolutionDto resolutionDto);
}