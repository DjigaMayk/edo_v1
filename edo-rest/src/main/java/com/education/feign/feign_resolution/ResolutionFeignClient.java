package com.education.feign.feign_resolution;

import com.education.model.dto.ResolutionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "edo-service", path = "/api/service/resolution")
public interface ResolutionFeignClient {
    @PostMapping
    ResolutionDto save(ResolutionDto resolutionDto);

    @GetMapping(value = "/byId/{id}")
    ResolutionDto findById(@PathVariable("id") Long id);

    @GetMapping(value = "/allByAppealIdNotArchived/{appealId}")
    List<ResolutionDto> findAllByAppealIdNotArchived(@PathVariable("appealId") Long appealId);

    @GetMapping(value = "/allWithFilterArchived/")
    List<ResolutionDto> findAllWithFilterArchived(@RequestParam(value = "filter", required = false) @Nullable String filter);
}