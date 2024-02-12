package com.education.service.author.impl;

import com.education.feign.feign_author.AuthorFeignService;
import com.education.service.AbstractService;
import com.education.service.author.AuthorService;
import com.education.model.dto.AuthorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AuthorServiceImpl extends AbstractService<AuthorFeignService, AuthorDto> implements AuthorService {

    private final AuthorFeignService authorFeignService;

    public AuthorServiceImpl(AuthorFeignService authorFeignService, AuthorFeignService authorFeignService1) {
        super(authorFeignService);
        this.authorFeignService = authorFeignService1;
    }

    public ResponseEntity<AuthorDto> saveAuthor(AuthorDto author) {
        return  new ResponseEntity<>(authorFeignService.saveAuthor(author), HttpStatus.CREATED);
    }

    @Override
    public List<AuthorDto> findAllById(List<Long> id) {
        return authorFeignService.showAllById(id);
    }

    @Override
    public List<AuthorDto> findAuthorByFIO(String fio) {
        return authorFeignService.findAuthorByFIO(fio);
    }
}
