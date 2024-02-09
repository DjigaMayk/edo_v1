package com.education.service.employee;

import com.education.entity.Employee;
import com.education.model.dto.EmployeeDto;
import com.education.service.BaseInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService extends BaseInterface<EmployeeDto> {

    void save(Employee emp);

    Employee findById(Long id);

    List<Employee> findAllById(Iterable<Long> ids);

    Employee findByIdNotArchived(Long id);

    List<Employee> findAllByIdNotArchived(Iterable<Long> ids);

    void moveToArchive(Long id);

    List<Employee> findAllByLastNameLikeOrderByLastName(String fio);
}
