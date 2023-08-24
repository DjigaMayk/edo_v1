package com.education.listener;

import com.education.entity.Employee;
import com.education.model.dto.EmployeeDto;
import com.education.repository.EmployeeRepository;
import com.education.util.Mapper.impl.EmployeeMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.education.model.constant.RabbitConstant.EXCHANGE;

@Service
public class EmployeeListener {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;


    public EmployeeListener(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @RabbitListener(queues = EXCHANGE)
    public void processEmployee(List<EmployeeDto> employeeDtoList) {
        for(EmployeeDto employeeDto : employeeDtoList) {
            Employee employee = employeeMapper.toEntity(employeeDto);
            employeeRepository.save(employee);
        }
    }
}
