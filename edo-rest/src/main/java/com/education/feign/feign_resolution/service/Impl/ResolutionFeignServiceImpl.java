package com.education.feign.feign_resolution.service.Impl;

import com.education.feign.feign_resolution.service.ResolutionFeignService;
import com.education.feign.feign_resolution.service.ResolutionService;
import com.education.model.dto.ResolutionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ResolutionFeignServiceImpl implements ResolutionService {
    private final ResolutionFeignService resolutionFeignService;

    @Override
    public ResolutionDto save(ResolutionDto resolutionDto) {
        return resolutionFeignService.save(resolutionDto);
    }
}