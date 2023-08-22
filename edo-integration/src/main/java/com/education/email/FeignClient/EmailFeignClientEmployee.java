package com.education.email.FeignClient;

import com.education.model.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Интерфейс EmailFeignClientEmployee.
 * Интерфейс для работы с модулем edo-repository через FeignClient
 */
@FeignClient(name = "edo-repositoryEmployee", path = "/edo-repository/api/repository/employee")
public interface EmailFeignClientEmployee {
    @GetMapping(value = "/{id}")
    EmployeeDto findById(@PathVariable("id") Long id);
}
