package com.education.controller;

import com.education.entity.Facsimile;
import com.education.model.dto.FacsimileDTO;
import com.education.service.facsimile.FacsimileService;
import com.education.util.Mapper.impl.FacsimileMapper;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO Task 98
 */
@RestController
@RequestMapping("/api/repository/facsimile")
@AllArgsConstructor
@Log
@ApiModel("Controller for Facsimile")
public class FacsimileController {

    @ApiModelProperty("service")
    private final FacsimileService facsimileService;

    @ApiModelProperty("mapper")
    private final FacsimileMapper facsimileMapper;

    /**
     * Method for getting facsimile
     *
     * @param id is Id facsimile in DB
     */
    @ApiOperation(value = "Получить факсимиле по id", notes = "Returns facsimile by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The Facsimile was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FacsimileDTO> getFacsimile(@PathVariable("id") Long id) {
        log.info("Request to get facsimile by id = " + id);

        var result = facsimileService.findById(id);
        if (result.isEmpty()) {
            log.warning("Facsimile not found");
        } else {
            log.info("Facsimile was found");
        }
        return result.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(facsimileMapper.toDto(result.get()), HttpStatus.OK);
    }

    /**
     * Method for saving Facsimile in DB
     *
     * @param facsimile Facsimile to save
     */
    @ApiOperation(value = "Сохранить факсимиле")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added")
    })
    @PostMapping("/")
    public ResponseEntity<FacsimileDTO> saveFacsimile(@RequestBody Facsimile facsimile) {
        log.info("Request for saving facsimile");

        Facsimile facsimileSaved = facsimileService.saveFacsimile(facsimile);
        if (facsimileSaved == null) {
            log.warning("Facsimile couldn't be saved");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            log.info("Facsimile saved");
            return new ResponseEntity<>(facsimileMapper.toDto(facsimileSaved), HttpStatus.CREATED);
        }
    }

}
