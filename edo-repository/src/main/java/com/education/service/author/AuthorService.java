package com.education.service.author;

import com.education.model.dto.AuthorDto;

import java.util.List;

/**
 * Интерфейс методов работы с БД для автора
 * Сервис
 * Модуль edo-repository
 */

public interface AuthorService {
    // save, delete, findById, findAllById
    AuthorDto save(AuthorDto author);
    void delete(Long id);
    AuthorDto findById(Long id);
    List<AuthorDto> findAllById(List<Long> id);
    List<AuthorDto> findAuthorByFIO(String fio);
}
