package com.education.controller;

import com.education.model.dto.ResolutionDto;
import com.education.service.resolution.ResolutionService;
import com.education.util.Mapper.impl.ResolutionMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.education.entity.Resolution;

import java.util.List;

@ApiOperation("Resolution API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/repository/resolution")
public class ResolutionController {

    final private ResolutionMapper mapper;
    final private ResolutionService resolutionService;

    @ApiOperation(value = "Сохранение сущности в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена"),
            @ApiResponse(code = 409, message = "Сущность не сохранена")
    })
    @PostMapping
    public ResponseEntity<ResolutionDto> saveResolution(@RequestBody ResolutionDto resolutionDto) {
        Resolution resolutionAfter = resolutionService.save(mapper.toEntity(resolutionDto));
        if (resolutionAfter.getId() != null) {
            log.info("Сущность сохранена или обновлена");
            return new ResponseEntity<>(mapper.toDto(resolutionAfter), HttpStatus.CREATED);
        }
        log.info("Сущность не сохранена и не обновлена");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ApiOperation(value = "Обновление даты архивации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
    })
    @PutMapping("/toArchive/{id}")
    public ResponseEntity<ResolutionDto> moveToArchiveResolution(@PathVariable Long id) {
        resolutionService.moveToArchive(id);
        log.log(Level.INFO, "Дата архивации обновлена");
        return new ResponseEntity<>(mapper.toDto(resolutionService.findById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление даты архивации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
    })
    @PutMapping("/fromArchive/{id}")
    public ResponseEntity<ResolutionDto> removeFromArchiveResolution(@PathVariable Long id) {
        resolutionService.removeFromArchive(id);
        log.log(Level.INFO, "Дата архивации удалена");
        return new ResponseEntity<>(mapper.toDto(resolutionService.findById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<ResolutionDto> findByIdResolution(@PathVariable Long id) {
        Resolution resolution = resolutionService.findById(id);
        if (resolution == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(mapper.toDto(resolution), HttpStatus.OK);

    }

    @ApiOperation(value = "Получение сущностей по списку id (/1, 2, 3)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allById/{ids}")
    public ResponseEntity<List<ResolutionDto>> findAllByIdResolution(@PathVariable List<Long> ids) {
        List<Resolution> resolution = resolutionService.findAllById(ids);
        if (resolution == null && resolution.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(mapper.toDto(resolution), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/notArchived/{id}")
    public ResponseEntity<ResolutionDto> findByIdNotArchivedResolution(@PathVariable Long id) {
        Resolution resolution = resolutionService.findByIdNotArchived(id);
        if (resolution == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(mapper.toDto(resolution), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по списку id (/1, 2) ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allNotArchived/{ids}")
    public ResponseEntity<List<ResolutionDto>> findAllByIdNotArchivedResolution(@PathVariable List<Long> ids) {
        List<Resolution> resolution = resolutionService.findAllByIdNotArchived(ids);
        if (resolution == null && resolution.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(mapper.toDto(resolution), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей по id обращения без даты архивации ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allByAppealIdNotArchived/{appealId}")
    public ResponseEntity<List<ResolutionDto>> findAllByAppealIdNotArchived(@PathVariable Long appealId) {
        List<Resolution> resolution = resolutionService.findAllByAppealIdNotArchived(appealId);
        if (resolution == null && resolution.isEmpty()) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(mapper.toDto(resolution), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение статуса черновика резолюции по id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдены"),
            @ApiResponse(code = 404, message = "Сущностьне найдены")
    })
    @GetMapping(value = "/getResolutionDraftStatus/{id}")
    public ResponseEntity<Boolean> getResolutionDraftStatus(@PathVariable Long id) {
        Boolean isDraft = resolutionService.isDraft(id);
        log.log(Level.INFO, "Статус получен");
        return new ResponseEntity<>(isDraft, HttpStatus.OK);
    }
}
