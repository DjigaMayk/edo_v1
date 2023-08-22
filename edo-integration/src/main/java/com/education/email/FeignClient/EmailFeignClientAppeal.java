package com.education.email.FeignClient;

import com.education.model.dto.AppealDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Интерфейс EmailFeignClientAppeal.
 * Интерфейс для работы с модулем edo-repository через FeignClient
 */
@FeignClient(name = "edo-repositoryAppeal", path = "/edo-repository/api/repository/appeal")
public interface EmailFeignClientAppeal {
    @GetMapping(value = "/byId/{id}")
    AppealDto findById(@PathVariable("id") Long id);
}
