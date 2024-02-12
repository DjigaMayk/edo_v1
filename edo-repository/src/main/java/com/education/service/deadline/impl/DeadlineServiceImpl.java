package com.education.service.deadline.impl;

import com.education.entity.Deadline;
import com.education.model.dto.DeadlineDto;
import com.education.repository.DeadlineRepository;
import com.education.service.AbstractService;
import com.education.service.deadline.DeadlineService;
import com.education.util.Mapper.impl.DeadlineMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeadlineServiceImpl extends AbstractService<DeadlineRepository, Deadline, DeadlineDto, DeadlineMapper> implements DeadlineService {

    private final DeadlineRepository deadlineRepository;
    private final DeadlineMapper mapper;

    public DeadlineServiceImpl(DeadlineRepository repository, DeadlineMapper deadlineMapper, DeadlineRepository deadlineRepository, DeadlineMapper mapper) {
        super(repository, deadlineMapper);
        this.deadlineRepository = deadlineRepository;
        this.mapper = mapper;
    }

    /**
     * Сохранение нового дедлайна
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeadlineDto save(DeadlineDto deadlineDto) {
        Deadline deadline = mapper.toEntity(deadlineDto);
        return mapper.toDto(deadlineRepository.save(deadline));
    }

    /**
     * Обновление дедлайна, установка комментария
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeadlineDto update(DeadlineDto deadlineDto) {
        Deadline deadline = mapper.toEntity(deadlineDto);
        return mapper.toDto(deadlineRepository.save(deadline));
    }

    /**
     * Удаление дедлайна по id
     */

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        deadlineRepository.deleteById(id);
    }

    /**
     * Поиск дедлайна по id
     */

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public DeadlineDto findById(Long id) {
        Optional<Deadline> deadline = deadlineRepository.findById(id);
        return deadline.map(mapper::toDto).orElse(null);
    }

    /**
     * Поиск дедлайнов по списку id
     */

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<DeadlineDto> findAllById(List<Long> ids) {
        List<Deadline> deadlines = deadlineRepository.findAllById(ids);
        return deadlines.isEmpty() ? null : mapper.toDto(deadlines);
    }
}
