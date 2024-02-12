package com.education.service;

import com.education.util.Mapper.Mappable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Набор стандартных методов обращения к БД для использования в сервисах.
 */

@Slf4j
@Service
@AllArgsConstructor
public abstract class AbstractService<Repository extends JpaRepository<Entity, Long>, Entity, Dto, Mapper extends Mappable<Entity, Dto>> {

    protected final Repository repository;

    protected final Mapper mapper;

    public Dto getById(Long id) {
        Entity entity = repository.getById(id);
        return mapper.toDto(entity);
    }

    public List<Dto> findAll() {
        List<Entity> entityList = repository.findAll();
        return mapper.toDto(entityList);
    }

    public Dto save(Dto dto) {
        Entity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
