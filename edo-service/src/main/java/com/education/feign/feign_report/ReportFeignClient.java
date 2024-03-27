package com.education.feign.feign_report;

import com.education.feign.AbstractFeign;
import com.education.model.dto.ReportDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alexey Pshenichny
 */
@FeignClient(name = "edo-repository", path = "/api/repository/report")
public interface ReportFeignClient extends AbstractFeign<ReportDto> {

    @GetMapping("/findAllByCreationDateEquals/{date}")
    List<ReportDto> findAllByCreationDateEquals(@PathVariable(value = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date);

    @GetMapping("/findAllByCreatorId/{id}")
    List<ReportDto> findAllByCreatorId(@PathVariable("id") Long creatorId);

    @GetMapping("/findAllByResolutionId/{id}")
    List<ReportDto> findAllByResolutionId(@PathVariable("id") Long resolutionId);

    @GetMapping("/findAllByIsResolutionCompletedFalse")
    List<ReportDto> findAllByIsResolutionCompletedFalse();

    @GetMapping("/findAllByIsResolutionCompletedTrue")
    List<ReportDto> findAllByIsResolutionCompletedTrue();

    @PutMapping("/{id}")
    ReportDto update(@PathVariable Long id, @RequestBody ReportDto reportDto);

}
