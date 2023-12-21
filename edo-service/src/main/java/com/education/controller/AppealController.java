package com.education.controller;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.model.util.exceptions.AppealNotValidException;
import com.education.model.enumEntity.EnumAppealStatus;
import com.education.service.appeal.AppealService;
import com.education.service.email.EmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("AppealDto API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/service/appeal")
public class AppealController {

    final private AppealService appealService;
    final private EmailService emailService;

    @ApiOperation(value = "Сохранение сущности в БД")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Сущность сохранена"),
            @ApiResponse(code = 409, message = "Сущность не сохранена")
    })
    @PostMapping
    public ResponseEntity<AppealDto> saveAppeal(@ApiParam("appealDto") @RequestBody AppealDto appealDto) {
        AppealDto appealAfter = appealService.save(appealDto);
        if (appealAfter.getId() != null) {
            if (EnumAppealStatus.NEW.equals(appealAfter.getAppealStatus())) {
                emailService.sendNotificationOnAppeal(appealAfter);
            }
            log.log(Level.INFO, "Сущность сохранена или обновлена");
            return new ResponseEntity<>(appealAfter, HttpStatus.CREATED);
        }
        log.log(Level.WARN, "Сущность не сохранена и не обновлена");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ApiOperation(value = "Обновление даты архивации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
    })
    @PutMapping("/toArchive/{id}")
    public ResponseEntity<AppealDto> moveToArchiveAppeal(@ApiParam("id") @PathVariable Long id) {
        appealService.moveToArchive(id);
        log.log(Level.INFO, "Дата архивации обновлена");
        return new ResponseEntity<>(appealService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/byId/{id}")
    public ResponseEntity<AppealDto> findByIdAppeal(@ApiParam("id") @PathVariable Long id) {
        AppealDto appeal = appealService.findById(id);
        if (appeal == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(appeal, HttpStatus.OK);

    }

    @ApiOperation(value = "Получение сущностей по списку id (/1, 2, 3)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })
    @GetMapping(value = "/allById/{idListAppeal}")
    public ResponseEntity<List<AppealDto>> findAllByIdAppeal(@ApiParam("idListAppeal") @PathVariable List<Long> idListAppeal) {
        List<AppealDto> appealDto = appealService.findAllById(idListAppeal);
        if (CollectionUtils.isEmpty(appealDto)) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(appealDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/notArchived/{id}")
    public ResponseEntity<AppealDto> findByIdNotArchivedAppeal(@ApiParam("id") @PathVariable Long id) {
        AppealDto appealDto = appealService.findByIdNotArchived(id);
        if (appealDto == null) {
            log.log(Level.WARN, "Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущность найдена");
        return new ResponseEntity<>(appealDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей без даты архивации по списку id (/1, 2) ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущности найдены"),
            @ApiResponse(code = 404, message = "Сущности не найдены")
    })

    @GetMapping(value = "/allNotArchived/{ids}")
    public ResponseEntity<List<AppealDto>> findAllByIdNotArchivedAppeal(@ApiParam("ids") @PathVariable List<Long> ids) {
        List<AppealDto> appealDto = appealService.findAllByIdNotArchived(ids);
        if (CollectionUtils.isEmpty(appealDto)) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(appealDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущностей Appeal для Employee creator (?startIndex=1&amount=1)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })

    @GetMapping(value = "/appealsByEmployee/")
    public ResponseEntity<List<AppealAbbreviatedDto>> findByIdEmployee(@RequestParam("startIndex") Long startIndex,
                                                                       @RequestParam("amount") Long amount) {
        List<AppealAbbreviatedDto> appeal = appealService.findAllByIdEmployee(startIndex, amount);
        if (CollectionUtils.isEmpty(appeal)) {
            log.log(Level.WARN, "Сущности не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Сущности найдены");
        return new ResponseEntity<>(appeal, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение сущности Appeal по резолюции")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность найдена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @GetMapping(value = "/byResolutionId/{resolutionId}")
    public ResponseEntity<AppealDto> findAppealByResolutionId(@PathVariable Long resolutionId) {
        var appeal = appealService.findAppealByResolutionId(resolutionId);
        if (appeal == null) {
            log.warn("Сущность не найдена");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Сущность найдена");
        return new ResponseEntity<>(appeal, HttpStatus.OK);
    }

    @ApiOperation(value = "Изменение статуса отправки обращения на isMessageSent")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Сущность изменена"),
            @ApiResponse(code = 404, message = "Сущность не найдена")
    })
    @PutMapping("/toMessageSent/{id}")
    public ResponseEntity<HttpStatus> moveToMessageSent(@PathVariable Long id) {
        appealService.markMailIsSent(id);
        log.log(Level.INFO, "Статус отправки обращения изменен");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> validationHandler(AppealNotValidException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}