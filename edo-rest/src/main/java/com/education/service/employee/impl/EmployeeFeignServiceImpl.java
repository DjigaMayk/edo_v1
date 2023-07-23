package com.education.service.employee.impl;

import com.education.client.feign.EmployeeFeignClient;
import com.education.model.dto.EmployeeDto;
import com.education.service.employee.EmployeeFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmployeeFeignServiceImpl implements EmployeeFeignService {

    private final EmployeeFeignClient employeeFeignClient;


    @Override
    public List<EmployeeDto> findAllByLastNameLikeOrderByLastName(String fio) {
        if (fio.length() < 3) {
            return null;
        }
        return employeeFeignClient.findAllByLastNameLikeOrderByLastName(fio);
    }
}
