package com.education.service.facsimile.impl;

import com.education.entity.Facsimile;
import com.education.repository.FacsimileRepository;
import com.education.service.facsimile.FacsimileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FacsimileServiceImpl implements FacsimileService {
    private final FacsimileRepository facsimileRepository;

    /**
     * Метод для поиска объекта Facsimile по id среди всех записей
     * @param id id объекта
     * @return объект Facsimile
     */
    @Override
    @Transactional(readOnly = true)
    public Facsimile findById(Long id) {
        return facsimileRepository.findById(id).orElse(new Facsimile());
    }

}
