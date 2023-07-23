package com.education.client.feign;

import com.education.model.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс EmployeeFeignClient предоставляет методы для отправки запросов в edo-repository
 * через FeignClient.
 */
@FeignClient(name = "edo-repository", qualifiers = "employeeFeignClient")
public interface EmployeeFeignClient {

    @GetMapping("/api/repository/employee")
    List<EmployeeDto> getAllEmployeeById(@RequestParam("ids") List<Long> ids,
                                         @RequestParam(name = "notArchivedOnly")
                                         boolean notArchivedOnly);

    @PostMapping("/api/repository/employee")
    EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto);

    @GetMapping("/api/repository/employee/{id}")
    EmployeeDto getEmployeeById(@PathVariable("id") Long id,
                                @RequestParam(name = "notArchivedOnly")
                                boolean notArchivedOnly);

    @DeleteMapping("/api/repository/employee/{id}")
    void moveToArchiveEmployeeById(@PathVariable("id") Long id);

    @GetMapping("/api/repository/employee/byFIO/")
    List<EmployeeDto> findAllByLastNameLikeOrderByLastName(@RequestParam("fio") String fio);
}
