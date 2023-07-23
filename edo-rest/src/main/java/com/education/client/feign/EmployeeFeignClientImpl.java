package com.education.client.feign;

import com.education.model.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeFeignClientImpl implements EmployeeFeignClient {

    /**
     * Объект интерфейса EmployeeFeignClient.
     */
    private final EmployeeFeignClient employeeFeignClient;

    /**
     * Конструктор класса EmployeeFeignClientImpl.
     */
    public EmployeeFeignClientImpl(EmployeeFeignClient employeeFeignClient) {
        this.employeeFeignClient = employeeFeignClient;
    }

    /**
     * Метод findAllByLastNameLikeOrderByLastName выполняет поиск в таблице сущностей Employee по ФИО.
     *
     * @param fio ФИО для поиска.
     * @return Список EmployeeDto, соответствующий сущности с указанным ФИО.
     */
    @Override
    public List<EmployeeDto> findAllByLastNameLikeOrderByLastName(String fio) {
        return employeeFeignClient.findAllByLastNameLikeOrderByLastName(fio);
    }
}
