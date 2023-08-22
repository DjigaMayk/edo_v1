package com.education.client.feign.employee;

import com.education.model.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс EmployeeFeignClient предоставляет методы для отправки запросов в edo-service
 * через FeignClient.
 */
@FeignClient(name = "edo-service", path = "/api/service", qualifiers = "employeeFeignClient")
public interface EmployeeFeignClient {

    @GetMapping("/employee")
    List<EmployeeDto> getAllEmployeeById(@RequestParam("ids") List<Long> ids,
                                         @RequestParam(name = "notArchivedOnly")
                                         boolean notArchivedOnly);

    @PostMapping("/employee")
    EmployeeDto saveEmployee(@RequestBody EmployeeDto employee);

    @GetMapping("/employee/{id}")
    EmployeeDto getEmployeeById(@PathVariable("id") Long id,
                                @RequestParam(name = "notArchivedOnly", defaultValue = "true")
                                boolean notArchivedOnly);

    @DeleteMapping("/employee/{id}")
    void moveEmployeeToArchive(@PathVariable("id") Long id);

    @GetMapping("/employee/byFIO/")
    List<EmployeeDto> findAllByLastNameLikeOrderByLastName(@RequestParam("fio") String fio);

}