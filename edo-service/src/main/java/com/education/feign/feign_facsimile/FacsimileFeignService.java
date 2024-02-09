package com.education.feign.feign_facsimile;


import com.education.feign.AbstractFeign;
import com.education.model.dto.FacsimileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Катя Трофимова
 * <p>
 * FeignClient for Facsimile
 */
@FeignClient(name = "edo-repository", path = "/api/repository/facsimile")
public interface FacsimileFeignService extends AbstractFeign<FacsimileDto> {

    @GetMapping("/{id}")
    FacsimileDto getFacsimile(@PathVariable("id") Long id);

    @PostMapping("/")
    FacsimileDto saveFacsimile(@RequestBody FacsimileDto facsimile);

    @DeleteMapping("/archive")
    FacsimileDto archiveFacsimile(@RequestBody FacsimileDto facsimile);

    @GetMapping("/by-employee/{id}")
    FacsimileDto getFacsimileByEmployeeId(@PathVariable("id") Long id);
}
