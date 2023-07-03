package com.education.service.facsimile.impl;

import com.education.entity.Facsimile;
import com.education.repository.FacsimileRepository;
import com.education.service.facsimile.FacsimileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Никита Бадеев
 * Сервис-класс для объекта Facsimile
 * TODO Task 98
 */
@Service
@RequiredArgsConstructor
public class FacsimileServiceImpl implements FacsimileService {

    /**
     * Объект класса-репозитория для сущности Facsimile
     */
    private final FacsimileRepository facsimileRepository;

    /**
     * Method for saving facsimile in DB
     * @param facsimile saving facsimile
     * @return saved facsimile
     */
    @Transactional(rollbackFor = {IOException.class})
    @Override
    public Facsimile saveFacsimile(Facsimile facsimile) {
        return facsimileRepository.saveAndFlush(facsimile);
    }

    /**
     * Method for archiving facsimile
     * @param id Long
     */
    @Transactional
    @Override
    public void moveToArchive(Long id) {
        facsimileRepository.moveToArchive(id);
    }

    @Transactional
    @Override
    public void moveFromArchive(Long id) {
        facsimileRepository.moveFromArchive(id);
    }

    /**
     * Method for getting Facsimile from DB by id
     * @param id - Entity's id
     * @return Facsimile
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Facsimile> findById(Long id) {
        return facsimileRepository.findById(id);
    }
}
