package com.education.service.author.impl;

import com.education.feign.feign_author.AuthorFeignClient;
import com.education.model.dto.AuthorDto;
import com.education.service.AbstractService;
import com.education.service.author.AuthorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AuthorServiceImpl extends AbstractService<AuthorFeignClient, AuthorDto> implements AuthorService {

    private final AuthorFeignClient authorFeignClient;

    public AuthorServiceImpl(AuthorFeignClient authorFeignClient, AuthorFeignClient feignClient) {
        super(authorFeignClient);
        this.authorFeignClient = feignClient;
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