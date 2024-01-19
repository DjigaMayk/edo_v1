package com.education.controller;

import com.education.model.dto.DepartmentDto;
import com.education.service.department.feign.DepartmentFeignService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/department")
@AllArgsConstructor
@Log
@ApiModel("Контроллер эдо-сервиса для сущности Department")
public class DepartmentController {

    private final DepartmentFeignService departmentFeignService;


    @ApiOperation(value = "Получить department по id", notes = "Returns an department as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        DepartmentDto dto = departmentFeignService.findById(id);
        if (dto == null) {
            log.warning("Did not receive department-dto");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получить department по id. Игнорирует отправленные в архив", notes = "Returns an department as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/notArchived/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentByIdNotArchived(@PathVariable Long id) {
        DepartmentDto dto = departmentFeignService.findByIdNotArchived(id);
        if (dto == null) {
            log.warning("Did not receive department-dto NotArchived");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получить список department", notes = "Находит department по их id. Возвращает списком List<departmentDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The addresses was not found")
    })
    @PostMapping("/findAll")
    public ResponseEntity<List<DepartmentDto>> getDepartmentList(@RequestBody List<Long> idList) {
        var list = departmentFeignService.findAllById(idList);
        if (list == null) {
            log.warning("Did not receive department-dto list");
        }
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "Получить список department, что ещё не в архиве", notes = "Находит department по их id. Возвращает списком List<departmentDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The addresses was not found")
    })
    @PostMapping("/findAll/notArchived")
    public ResponseEntity<List<DepartmentDto>> getDepartmentListNotArchived(@RequestBody List<Long> idList) {
        var list = departmentFeignService.findAllByIdNotArchived(idList);
        if (list == null) {
            log.warning("отправил list DepartmentDto.class NotArchived");
        }
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "Сохранить адрес")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    @PostMapping("/")
    public ResponseEntity<DepartmentDto> save(@RequestBody @ApiParam("Address") DepartmentDto address) {
        var dto = departmentFeignService.save(address);
        if (dto.getClass() == DepartmentDto.class) {
            log.info("Saved DepartmentDto.class");
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            log.warning("Failed to save DepartmentDto.class");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "Назначить дату архивации department-а")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Департамент успешно архивирован")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDto> delete(@PathVariable Long id) {
        departmentFeignService.moveToArchive(id);
        log.info("Move entity department with id: %s to archive");
        return new ResponseEntity<>(departmentFeignService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Получить department по fullName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The address was not found")
    })
    @GetMapping("/")
    public ResponseEntity<DepartmentDto> findByFullName(@RequestParam(value = "fullName") String fullName) {
        DepartmentDto dto = departmentFeignService.findByFullName(fullName);
        if (dto == null) {
            log.warning("Did not receive department-dto");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
