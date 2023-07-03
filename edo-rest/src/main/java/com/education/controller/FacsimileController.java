package com.education.controller;

import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import com.education.service.facsimile.FacsimileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Никита Бадеев
 * RestController для сохранения факсимиле
 */
@Log
@ApiOperation("Facsimile API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/rest/facsimile")
public class FacsimileController {

    private final FacsimileService facsimileService;

    /**
     * Method for saving Facsimile image as file in file-storage
     * @param multipartFacsimile facsimile image
     * @return FilePoolDto
     */
    @ApiOperation(value = "Сохранение Факсимиле")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Facsimile saved"),
            @ApiResponse(code = 404, message = "Facsimile not found")
    })
    @PostMapping
    public ResponseEntity<FilePoolDto> saveFacsimile(@RequestPart("facsimile") MultipartFile multipartFacsimile) {
        log.info("Request for saving facsimile");
        if (multipartFacsimile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(facsimileService.saveFacsimile(multipartFacsimile));
    }

    /**
     * Method for saving facsimile as entity
     * @param jsonFile file with employee, department and filePool
     * @return FacsimileDto
     */
    @ApiOperation(value = "Сохранение сущности")
    @PostMapping("/json")
    public ResponseEntity<FacsimileDTO> createFacsimile(@RequestBody String jsonFile) {
        log.info("Request for saving facsimile entity");
        return ResponseEntity.ok().body(facsimileService.saveFacsimileEntity(jsonFile));
    }

    @ApiOperation(value = "Архивация факсимиле")
    @DeleteMapping("/archive")
    public ResponseEntity<FacsimileDTO> archiveFacsimile(@RequestBody String jsonFile) {
        log.info("Request to archive facsimile");
        return ResponseEntity.ok().body(facsimileService.archiveFacsimile(jsonFile));
    }
}
