package com.education.service.employee.impl;

import com.education.feign.feign_employee.EmployeeFeignClient;
import com.education.model.dto.EmployeeDto;
import com.education.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeFeignClient employeeFeignClient;

    @Override
    public EmployeeDto findById(Long id, boolean notArchivedOnly) {
        return employeeFeignClient.getEmployeeById(id, notArchivedOnly);
    }

    @Override
    public List<EmployeeDto> findAllById(List<Long> ids, boolean notArchivedOnly) {
        return employeeFeignClient.getAllEmployeeById(ids, notArchivedOnly);
    }

    @Override
    public EmployeeDto save(EmployeeDto emp) {
        return employeeFeignClient.saveEmployee(emp);
    }

    @Override
    public void moveToArchive(Long id) {
        employeeFeignClient.moveEmployeeToArchive(id);
    }

    @Override
    public List<EmployeeDto> findAllByLastNameLikeOrderByLastName(String fio) {
        return fio.length() < 3 ? null : employeeFeignClient.findAllByLastNameLikeOrderByLastName(fio);
    }
}
