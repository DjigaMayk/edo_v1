package com.education.controller;

import com.education.feign.feign_department.DepartmentFeignClient;
import com.education.model.dto.DepartmentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

@ApiModel("Department API")
@AllArgsConstructor
@RestController
@RequestMapping("api/rest/department")
@Log
@Log4j2
public class DepartmentController {
    private final DepartmentFeignClient departmentFeignClient;

    @ApiOperation("Получить сущность Department по fullName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Department was successfully found"),
            @ApiResponse(code = 400, message = "The request is too short. A request of 3 characters was expected"),
            @ApiResponse(code = 404, message = "Department was not found")})
    @GetMapping
    public ResponseEntity<DepartmentDto> findByFullName(@RequestParam(value = "fullName") String fullName) {
        log.log(Level.INFO, "Получен запрос на поиск сущностей с fullName = {0}", fullName);
        if (fullName.length() < 3) {
            log.warning("Слишком короткий запрос. Ожидался запрос от 3 символов");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DepartmentDto dto = departmentFeignClient.findByFullName(fullName);
        log.log(dto == null
                        ? Level.WARNING
                        : Level.INFO
                , "Результат поиска: {0}", dto);
        return new ResponseEntity<>(
                dto
                , dto == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
