package com.education.service.employee.impl;

import com.education.feign.feign_employee.EmployeeFeignClient;
import com.education.model.dto.EmployeeDto;
import com.education.service.AbstractService;
import com.education.service.employee.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends AbstractService<EmployeeFeignClient, EmployeeDto> implements EmployeeService {

    private final EmployeeFeignClient employeeFeignClient;

    private static final int FIOLENGTH = 3;

    public EmployeeServiceImpl(EmployeeFeignClient employeeFeignClient, EmployeeFeignClient employeeFeignClient1) {
        super(employeeFeignClient);
        this.employeeFeignClient = employeeFeignClient1;
    }

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
        employeeFeignClient.moveToArchiveEmployeeById(id);
    }

    @Override
    public List<EmployeeDto> findAllByLastNameLikeOrderByLastName(String fio) {
        return fio.length() < FIOLENGTH ? null : employeeFeignClient.findAllByLastNameLikeOrderByLastName(fio);
    }

}
