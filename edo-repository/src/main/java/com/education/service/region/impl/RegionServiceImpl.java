package com.education.service.region.impl;

import com.education.entity.Region;
import com.education.model.dto.RegionDto;
import com.education.repository.RegionRepository;
import com.education.service.AbstractService;
import com.education.service.region.RegionService;
import com.education.util.Mapper.impl.RegionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl extends AbstractService<RegionRepository, Region, RegionDto, RegionMapper> implements RegionService {
    /**
     * Репозиторий для работы с БД
     */
    private final RegionRepository repository;
    /**
     * Конвертер ДТО в Энтити и наоборот
     */
    private final RegionMapper mapper;

    public RegionServiceImpl(RegionRepository repository, RegionMapper regionMapper, RegionRepository repository1, RegionMapper mapper) {
        super(repository, regionMapper);
        this.repository = repository1;
        this.mapper = mapper;
    }

    /**
     * Метод для сохранения объекта Region в БД.
     *
     * @param region
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegionDto save(RegionDto region) {
        repository.save(mapper.toEntity(region));
        return region;
    }

    /**
     * Метод для поиска объекта Region в БД по id.
     *
     * @param id
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Optional<Region> findById(long id) {
        return repository.findById(id);
    }

    /**
     * Метод для вывода доступных объектов Region в БД.
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<RegionDto> findAll() {
        return mapper.toDto(repository.findAll());
    }

    /**
     * Метод для удаления объекта Region в БД по id.
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
