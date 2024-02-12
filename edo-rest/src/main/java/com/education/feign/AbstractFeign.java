package com.education.feign;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Набор стандартных методов для обращения к Client.
 */

public interface AbstractFeign<Dto> {

    @GetMapping("/{id}")
    Dto getById(@PathVariable("id") Long id);

    @GetMapping("/findAll")
    List<Dto> findAll();

    @PostMapping
    Dto save(@RequestBody Dto dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}
