package com.education.service.author.impl;

import com.education.entity.Author;
import com.education.model.dto.AuthorDto;
import com.education.repository.AuthorRepository;
import com.education.service.AbstractService;
import com.education.service.author.AuthorService;
import com.education.util.Mapper.impl.AuthorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Имплементация интерфейса методов для работы в БД с сущностью Автора
 */

@Service
public class AuthorServiceImpl extends AbstractService<AuthorRepository, Author, AuthorDto, AuthorMapper> implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper mapper;

    public AuthorServiceImpl(AuthorRepository repository, AuthorMapper authorMapper, AuthorRepository authorRepository, AuthorMapper mapper) {
        super(repository, authorMapper);
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    /**
     * Поиск сущностей по значениям их id
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<AuthorDto> findAllById(List<Long> ids) {
        List<Author> authorList = authorRepository.findAllById(ids);

        if (authorList.isEmpty()) {
            return null;
        }
        return authorList
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Метод, производящий поиск в таблице сущностей Author по введенным символам
     *
     * @param fio
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> findAuthorByFIO(String fio) {
        return authorRepository.findAuthorByFIO(fio).stream().map(mapper::toDto).toList();
    }


}
