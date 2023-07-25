package com.education.service.employee.impl;

import com.education.client.feign.EmployeeFeignClient;
import com.education.model.dto.EmployeeDto;
import com.education.service.employee.EmployeeFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeFeignServiceImpl implements EmployeeFeignService {

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
