package com.education.feign.feign_appeal;

import com.education.feign.AbstractFeign;
import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "edo-service", path = "/api/service/appeal")
public interface AppealFeignClient extends AbstractFeign<AppealDto> {
    @PostMapping
    AppealDto createAppeal(AppealDto appealDto);

    @GetMapping(value = "/appealsByEmployee/")
    List<AppealAbbreviatedDto> findAllByIdEmployee(@RequestParam("startIndex") Long startIndex,
                                                   @RequestParam("amount") Long amount);

    @GetMapping(value = "/byId/{id}")
    AppealDto findById(@PathVariable("id") Long id);
}