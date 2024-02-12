package com.education.service;

import java.util.List;

/**
 * Базовый интерфейс для работы с AbstractService.
 */

public interface BaseInterface<Dto> {

    Dto getById(Long id);

    List<Dto> findAll();

    Dto save(Dto dto);

    void delete(Long id);
}