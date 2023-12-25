package com.education.service.author;

import com.education.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto save(AuthorDto author);
    void delete(Long id);
    AuthorDto findById(Long id);
    List<AuthorDto> findAllById(List<Long> id);
    List<AuthorDto> findAuthorByFIO(String fio);
}
