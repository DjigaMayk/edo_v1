package com.education.service.author;

import com.education.model.dto.AuthorDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Интерфейс методов работы с БД для автора
 * Сервис
 * Модуль edo-service
 */
public interface AuthorService {
    ResponseEntity<AuthorDto> save(AuthorDto author);
    ResponseEntity<String> delete(Long id);
    AuthorDto findById(Long id);
    List<AuthorDto> findAllById(List<Long> id);
    List<AuthorDto> findAuthorByFIO(String fio);
}
