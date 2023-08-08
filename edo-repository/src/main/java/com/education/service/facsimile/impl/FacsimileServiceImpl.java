package com.education.service.facsimile.impl;

import com.education.entity.Facsimile;
import com.education.repository.FacsimileRepository;
import com.education.service.facsimile.FacsimileService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Log
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
//        Facsimile test = facsimileRepository.findFacsimileByEmployeeId(id);
//
//        log.info("Facsimile_ID - " + test.getId());
//        log.info("Employee_ID - " + test.getEmployee().getId());
//        log.info("Department_name - " + test.getDepartment().getFullName());
//        log.info("File_ID - " + test.getFile().getId()); // NullPointerException
//
//        return test;
        return facsimileRepository.findFacsimileByEmployeeId(id);
    }

}
