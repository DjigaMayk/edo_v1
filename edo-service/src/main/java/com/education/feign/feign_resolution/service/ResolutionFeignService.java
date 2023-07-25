package com.education.feign.feign_resolution.service;

import com.education.model.dto.ResolutionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "edo-repository", path = "/api/repository/resolution")
public interface ResolutionFeignService {
    @PostMapping
    ResolutionDto save(ResolutionDto resolution);
    @PutMapping("/toArchive/{id}")
    void moveToArchive(@PathVariable("id") Long id);
    @GetMapping("/byId/{id}")
    ResolutionDto findById(@PathVariable("id") Long id);
    @GetMapping("/allById/{ids}")
    List<ResolutionDto> findAllById(@PathVariable("ids") Iterable<Long> ids);
    @GetMapping(value = "/notArchived/{id}")
    ResolutionDto findByIdNotArchived(@PathVariable("id") Long id);
    @GetMapping(value = "/allNotArchived/{ids}")
    List<ResolutionDto> findAllByIdNotArchived(@PathVariable("ids") Iterable<Long> ids);
}