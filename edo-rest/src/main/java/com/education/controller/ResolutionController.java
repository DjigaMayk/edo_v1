package com.education.controller;

import com.education.model.dto.ResolutionDto;
import com.education.service.Resolution.ResolutionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log
@RequestMapping("api/rest/resolution")
public class ResolutionController {

    private final ResolutionService service;

    @ApiOperation(value = "Сохранение резолюции")
    @PostMapping
    public ResponseEntity<ResolutionDto> createNewResolution(@RequestBody ResolutionDto resolutionDto) {
        log.info("Получен запрос на сохранение резолюции");
        ResolutionDto resolutionDtoAfter = service.save(resolutionDto);
        return new ResponseEntity<>(resolutionDtoAfter, HttpStatus.CREATED);
    }
}
