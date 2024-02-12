package com.education.service.employee;

import com.education.model.dto.EmployeeDto;
import com.education.service.BaseInterface;

import java.util.List;

public interface EmployeeService extends BaseInterface<EmployeeDto> {

    EmployeeDto findById(Long id, boolean notArchivedOnly);

    List<EmployeeDto> findAllById(List<Long> ids, boolean notArchivedOnly);

    EmployeeDto save(EmployeeDto emp);

    void moveToArchive(Long id);

    List<EmployeeDto> findAllByLastNameLikeOrderByLastName(String fio);

}
