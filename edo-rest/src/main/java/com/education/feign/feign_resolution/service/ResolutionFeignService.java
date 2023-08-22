package com.education.feign.feign_resolution.service;

import com.education.model.dto.ResolutionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "edo-service", path = "/api/service/resolution")
public interface ResolutionFeignService {
    @PostMapping
    ResolutionDto save(ResolutionDto resolutionDto);
}