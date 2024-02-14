package com.education.service.author;

import com.education.model.dto.AuthorDto;
import com.education.service.BaseInterface;

import java.util.List;

/**
 * Интерфейс методов работы с БД для автора
 * Сервис
 * Модуль edo-repository
 */

public interface AuthorService extends BaseInterface<AuthorDto> {

    List<AuthorDto> findAllById(List<Long> id);

    List<AuthorDto> findAuthorByFIO(String fio);
}
