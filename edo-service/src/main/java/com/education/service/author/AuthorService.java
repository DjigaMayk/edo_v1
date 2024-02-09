package com.education.service.author;

import com.education.model.dto.AuthorDto;
import com.education.service.BaseInterface;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Интерфейс методов работы с БД для автора
 * Сервис
 * Модуль edo-service
 */
public interface AuthorService extends BaseInterface<AuthorDto> {

    ResponseEntity<AuthorDto> saveAuthor(AuthorDto author);

    List<AuthorDto> findAllById(List<Long> id);

    List<AuthorDto> findAuthorByFIO(String fio);
}
