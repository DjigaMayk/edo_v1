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
    /**
     * Метод для поиска объекта Facsimile по employee_id среди всех записей
     * @param id employee_id объекта
     * @return объект Facsimile
     */
    @Override
    @Transactional(readOnly = true)
    public Facsimile findFacsimileByEmployeeId(Long id) {
        return facsimileRepository.findFacsimileByEmployeeId(id);
    }

}
