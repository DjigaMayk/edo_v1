package com.education.controller;

import com.education.feign.feign_employee.EmployeeFeignClient;
import com.education.model.dto.EmployeeDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("api/service/employee")
@Log
@AllArgsConstructor
@ApiModel("Контроллер сервиса для сущности Employee")
public class EmployeeController {

    private final EmployeeFeignClient employeeFeignClient;

    @ApiOperation("Получить сущность Employee по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee was successfully found"),
            @ApiResponse(code = 404, message = "Employee was not found")})
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id
            , @RequestParam(name = "notArchivedOnly", defaultValue = "true") boolean notArchivedOnly) {
        log.log(Level.INFO, "Получен запрос на поиск сущности с id = {0}", id);
        EmployeeDto employeeDto = employeeFeignClient.getEmployeeById(id, notArchivedOnly);
        log.log(employeeDto != null
                        ? Level.INFO
                        : Level.WARNING
                , "Результат поиска: {0}", employeeDto);
        return new ResponseEntity<>(employeeDto
                , employeeDto != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Получить несколько сущностей Employee по id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Employee was successfully found"),
            @ApiResponse(code = 404, message = "Employee was not found")})
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeById(@RequestParam("ids") List<Long> ids
            , @RequestParam(name = "notArchivedOnly", defaultValue = "true") boolean notArchivedOnly) {
        log.log(Level.INFO, "Получен запрос на поиск сущностей с id = {0}", ids);
        List<EmployeeDto> employeeDtos = employeeFeignClient.getAllEmployeeById(ids, notArchivedOnly);
        log.log(!employeeDtos.isEmpty()
                        ? Level.INFO
                        : Level.WARNING
                , "Результат поиска: {0}", employeeDtos);
        return new ResponseEntity<>(
                employeeDtos
                , !employeeDtos.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Сохранить сущность employee")
    @ApiResponse(code = 201, message = "Employee was saved")
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employee) {
        log.log(Level.INFO, "Получен запрос на сохранение сущности");
        EmployeeDto emp = employeeFeignClient.saveEmployee(employee);
        log.log(Level.INFO, "Сущность сохранена");
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    @ApiOperation("Отправить сущность employee в архив")
    @ApiResponse(code = 200, message = "Employee was moved to archive")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> moveToArchiveEmployeeById(@PathVariable("id") Long id) {
        log.log(Level.INFO, "Получен запрос добавление сущности в архив");
        employeeFeignClient.moveToArchiveEmployeeById(id);
        log.log(Level.INFO, "Сущность успешно добавлена в архив");
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @ApiOperation("Поиск пользователя по ФИО")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee was successfully found"),
            @ApiResponse(code = 404, message = "Employee was not found")})
    @GetMapping("/byFIO/")
    public ResponseEntity<List<EmployeeDto>> userSearch(@RequestParam(value = "fio", required = false) String fio) {
        log.log(Level.INFO, "Получен запрос на поиск сущностей {0}", fio);
        List<EmployeeDto> listDTO = employeeFeignClient.findAllByLastNameLikeOrderByLastName(fio);
        log.log(!listDTO.isEmpty()
                        ? Level.INFO
                        : Level.WARNING
                , "Результат поиска сущностей: {0}", listDTO);
        return new ResponseEntity<>(listDTO
                , !listDTO.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
