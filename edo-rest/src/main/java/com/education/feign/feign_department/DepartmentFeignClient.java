package com.education.feign.feign_department;

import com.education.model.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "edo-service", path = "/api/service")
public interface DepartmentFeignClient {
    @GetMapping("/department/")
    DepartmentDto findByFullName(@RequestParam(value = "fullName") String fullName);
}
