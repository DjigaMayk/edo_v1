package com.education.service.author.impl;

import com.education.feign.feign_author.AuthorFeignClient;
import com.education.model.dto.AuthorDto;
import com.education.service.author.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class AuthorServiceImpl implements AuthorService {

    private final AuthorFeignClient authorFeignClient;

    @Override
    public AuthorDto save(AuthorDto author) {
        return authorFeignClient.saveAuthor(author);
    }

    @Override
    public void delete(Long id) {
        authorFeignClient.deleteAuthor(id);
    }

    @Override
    public AuthorDto findById(Long id) {
        return authorFeignClient.showById(id);
    }

    @Override
    public List<AuthorDto> findAllById(List<Long> id) {
        return authorFeignClient.showAllById(id);
    }

    @Override
    public List<AuthorDto> findAuthorByFIO(String fio) {
        return authorFeignClient.findAuthorByFIO(fio);
    }
}
