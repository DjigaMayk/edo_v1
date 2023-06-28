package com.education.controller;

import com.education.model.dto.DepartmentDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FacsimileDTO;
import com.education.service.facsimile.FacsimileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log
@ApiOperation("Facsimile API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/rest/facsimile")
public class FacsimileController {

    private final FacsimileService facsimileService;

    @ApiOperation(value = "Сохранение Факсимиле")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Facsimile saved"),
            @ApiResponse(code = 404, message = "Facsimile not found")
    })
    @PostMapping
    public ResponseEntity<FacsimileDTO> saveFacsimile(@RequestPart("facsimile") MultipartFile multipartFacsimile,
                                                      @RequestPart("jsonFile") String jsonFile) {
        log.info("Request for saving facsimile");
        if (multipartFacsimile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(facsimileService.saveFacsimile(multipartFacsimile, jsonFile));
    }
}
