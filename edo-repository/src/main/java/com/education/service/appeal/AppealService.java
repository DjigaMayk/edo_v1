package com.education.service.appeal;


import com.education.entity.Appeal;

import java.util.List;

public interface AppealService {
    void save(Appeal appeal);

    void moveToArchive(Long id);

    Appeal findById(Long id);

    List<Appeal> findAllById(Iterable<Long> ids);

    Appeal findByIdNotArchived(Long id);

    List<Appeal> findAllByIdNotArchived(Iterable<Long> ids);
}