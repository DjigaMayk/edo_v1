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
@FeignClient(name = "edo-service", path = "/api/service/report")
public interface ReportFeignClient extends AbstractFeign<ReportDto> {

    @GetMapping("/allByCreationDateEquals/{date}")
    List<ReportDto> findAllByCreationDateEquals(@PathVariable(value = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date);

    @GetMapping("/allByCreatorId/{id}")
    List<ReportDto> findAllByCreatorId(@PathVariable("id") Long creatorId);

    @GetMapping("/allByResolutionId/{id}")
    List<ReportDto> findAllByResolutionId(@PathVariable("id") Long resolutionId);

    @GetMapping("/allByIsResolutionCompletedFalse")
    List<ReportDto> findAllByIsResolutionCompletedFalse();

    @GetMapping("/allByIsResolutionCompletedTrue")
    List<ReportDto> findAllByIsResolutionCompletedTrue();

    @Override
    @GetMapping("/{id}")
    ReportDto getById(@PathVariable(value = "id") Long id);

    @Override
    @GetMapping("/all")
    List<ReportDto> findAll();

    @Override
    @PostMapping("/save")
    ReportDto save(@RequestBody ReportDto reportDto);

    @PutMapping("/update")
    ReportDto update(@RequestBody ReportDto reportDto);

    @Override
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable(value = "id") Long id);

}
