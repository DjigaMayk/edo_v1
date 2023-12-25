package com.education.feign.feign_author;

import com.education.model.dto.AuthorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign клиент для взаимодействия с микросервисом 'edo-repository' для работы с сущностью 'Author'.
 * Данный интерфейс предоставляет методы для выполнения различных операций CRUD (Create, Read, Update, Delete)
 * над сущностью 'Author'.
 */
@FeignClient(name = "edo-repository", path = "/api/repository/author")
public interface AuthorFeignService {


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
