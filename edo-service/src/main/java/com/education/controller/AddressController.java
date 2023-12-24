package com.education.controller;

import com.education.model.dto.AddressDto;
import com.education.service.address.AddressService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/address")
@AllArgsConstructor
@ApiModel("Контроллер эдо-сервиса для сущности Адрес")
@Log
public class AddressController {
    @ApiModelProperty("сервис для контроллера")
    private AddressService service;

    @ApiOperation(value = "Получить адрес по d", notes = "Returns an address as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long id) {
        log.info("отправил AddressDto.class");
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список адресов", notes = "Находит адреса по их id. Возвращает списком List<Address>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The addresses was not found")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<AddressDto>> getAddressesList(@RequestBody List<Long> idList) {
        log.info("отправил list AddressDto.class");
        return new ResponseEntity<>(service.fetchAddressesList(idList), HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранить адрес")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created")
    })
    @PostMapping("/")
    public ResponseEntity<AddressDto> save(@RequestBody @ApiParam("Address") AddressDto address) {
        log.info("сохранил AddressDto.class");
        return new ResponseEntity<>(service.save(address), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Удалить адрес")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @ApiParam("Address") Long id) {
        service.delete(id);
        log.info("удалил AddressDto.class");
        return ResponseEntity.ok().build();
    }
}
