package com.education.controller;

import com.education.model.dto.ResolutionDto;
import com.education.service.Resolution.ResolutionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
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

    @ApiOperation(value = "Получение сущности по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<ResolutionDto> findByIdResolution(@ApiParam("id") @PathVariable Long id) {
        ResolutionDto resolution = service.findById(id);
        if (resolution == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(resolution, HttpStatus.OK);

    }

    @ApiOperation(value = "Получение сущностей по id обращения без даты архивации ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allByAppealIdNotArchived/{appealId}")
    public ResponseEntity<List<ResolutionDto>> findAllByAppealIdNotArchived(@PathVariable Long appealId) {
        List<ResolutionDto> resolutionDto = service.findAllByAppealIdNotArchived(appealId);
        if (resolutionDto == null || resolutionDto.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получить все резолюции включая архивные (?filter=all или запрос без параметра), " +
            "или без них (?filter=nonarchived), или только архивные (?filter=archived)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Резолюции найдены"),
            @ApiResponse(code = 400, message = "Фильтр указан некорректно"),
            @ApiResponse(code = 404, message = "Резолюции не найдены")
    })
    @GetMapping(value = "/allWithFilterArchived/")
    public ResponseEntity<List<ResolutionDto>> findAllWithFilterArchived(
            @RequestParam(value = "filter", required = false) @Nullable String filter
    ) {
        if (filter == null || filter.equals("all") || filter.equals("nonarchived") || filter.equals("archived")) {
            List<ResolutionDto> resolutionsDto = service.findAllWithFilterArchived(filter);
            if (resolutionsDto == null || resolutionsDto.isEmpty()) {
                log.log(Level.WARN, "Сущности не найдены");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            log.log(Level.INFO, "Сущности найдены");
            return new ResponseEntity<>(resolutionsDto, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
