package com.education.controller;

import com.education.model.dto.FacsimileDto;
import com.education.service.facsimile.FacsimileService;
import com.education.util.Mapper.impl.FacsimileMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;


@RestController
@RequestMapping("api/repository/facsimile")
@AllArgsConstructor
@Log
@ApiModel("Контроллер репозитория для сущности Facsimile")
public class FacsimileController {
    @ApiModelProperty("Сервис репозитория для сущности Facsimile")
    private final FacsimileService facsimileService;

    private final FacsimileMapper mapper;

    @ApiOperation("Получить сущность Facsimile по id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Facsimile was successfully found"),
            @ApiResponse(code = 404, message = "Facsimile was not found")})
    @GetMapping("/{id}")
    public ResponseEntity<FacsimileDto> getFacsimileById(
            @PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос на поиск сущности с id = {0}", id);
        FacsimileDto facsimileDto = mapper
                .toDto(facsimileService.findById(id));
        log.log(facsimileDto != null
                        ? Level.INFO
                        : Level.WARNING
                , "Результат поиска: {0}", facsimileDto);
        return new ResponseEntity<>(facsimileDto
                , facsimileDto != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}