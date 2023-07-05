package com.education.service.facsimile.impl;

import com.education.entity.Facsimile;
import com.education.repository.FacsimileRepository;
import com.education.repository.FilePoolRepository;
import com.education.service.facsimile.FacsimileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Никита Бадеев
 *
 * Class-service for Facsimile
 */
@Service
@RequiredArgsConstructor
public class FacsimileServiceImpl implements FacsimileService {

    /**
     * Class-repository object for Facsimile
     */
    private final FacsimileRepository facsimileRepository;

    private final FilePoolRepository filePoolRepository;

    /**
     * Method for saving facsimile in DB
     *
     * @param facsimile saving facsimile
     * @return saved facsimile
     */
    @Transactional(rollbackFor = {IOException.class})
    @Override
    public Facsimile saveFacsimile(Facsimile facsimile) {
        return facsimileRepository.saveAndFlush(facsimile);
    }


    /**
     * Method for archiving/unarchiving facsimile
     *
     * @param id Long
     * @param isArchived boolean
     */
    @Transactional
    @Override
    public void moveInArchive(Long id, boolean isArchived) {
        facsimileRepository.moveInArchive(id, isArchived);
    }

    /**
     * Method for getting Facsimile from DB by id
     *
     * @param id - Entity's id
     * @return Facsimile
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Facsimile> findById(Long id) {
        return facsimileRepository.findById(id);
    }
}
