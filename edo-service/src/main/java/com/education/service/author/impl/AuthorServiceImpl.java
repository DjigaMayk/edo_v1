package com.education.service.author.impl;

import com.education.feign.feign_author.AuthorFeignService;
import com.education.service.author.AuthorService;
import com.education.model.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class AuthorServiceImpl implements AuthorService {

    private final AuthorFeignService authorFeignService;


    @Override
    public ResponseEntity<AuthorDto> save(AuthorDto author) {
        return  new ResponseEntity<>(authorFeignService.saveAuthor(author), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        authorFeignService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public AuthorDto findById(Long id) {
        return authorFeignService.showById(id);
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
