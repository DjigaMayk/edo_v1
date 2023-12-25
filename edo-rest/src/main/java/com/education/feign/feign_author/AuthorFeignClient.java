package com.education.feign.feign_author;

import com.education.model.dto.AuthorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient(name = "edo-service", path = "/api/service/author")
public interface AuthorFeignClient {
    @GetMapping("/{id}")
    AuthorDto showById(@PathVariable("id") Long id);

    @PostMapping("/findAll")
    List<AuthorDto> showAllById(@RequestBody List<Long> ids);

    @PostMapping
    AuthorDto saveAuthor(@RequestBody AuthorDto authorDto);

    @DeleteMapping("/{id}")
    void deleteAuthor(@PathVariable("id") Long id);

    @GetMapping("/byFIO/")
    List<AuthorDto> findAuthorByFIO(@RequestParam(value = "fio", required = false) String fio);
}
