package com.education.client.feign;

import com.education.model.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Интерфейс EmployeeFeignClient предоставляет методы для отправки запросов в edo-service
 * через FeignClient.
 */
@FeignClient(name = "edo-service", qualifiers = "employeeFeignClient")
public interface EmployeeFeignClient {

    @GetMapping("/api/service/employee/byFIO/")
    List<EmployeeDto> findAllByLastNameLikeOrderByLastName(@RequestParam("fio") String fio);

}
