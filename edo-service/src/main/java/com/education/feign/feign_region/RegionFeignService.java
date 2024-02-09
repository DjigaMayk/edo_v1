package com.education.feign.feign_region;

import com.education.feign.AbstractFeign;
import com.education.model.dto.RegionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "edo-repository", path = "/api/repository/region")
public interface RegionFeignService  extends AbstractFeign<RegionDto> {

    @PostMapping
    RegionDto saveRegion(RegionDto regionDto);

    @GetMapping(value = "/byId/{id}")
    RegionDto findByIdRegion(@PathVariable Long id);

    @GetMapping
    List<RegionDto> findAll();

    @DeleteMapping(value = "/byId/{id}")
    ResponseEntity<HttpStatus> deleteByIdRegion(@PathVariable Long id);

}
