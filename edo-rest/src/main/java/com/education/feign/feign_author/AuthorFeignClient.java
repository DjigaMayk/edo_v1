package com.education.feign.feign_author;

import com.education.feign.AbstractFeign;
import com.education.model.dto.AuthorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "edo-service", path = "/api/service/author")
public interface AuthorFeignClient extends AbstractFeign<AuthorDto> {

    @PostMapping("/findAll")
    List<AuthorDto> showAllById(@RequestBody List<Long> ids);

    @GetMapping("/byFIO/")
    List<AuthorDto> findAuthorByFIO(@RequestParam(value = "fio", required = false) String fio);
}
