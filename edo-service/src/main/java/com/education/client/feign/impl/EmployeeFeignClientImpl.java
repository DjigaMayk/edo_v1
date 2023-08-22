package com.education.client.feign.impl;

import com.education.client.feign.EmployeeFeignClient;
import com.education.model.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс EmployeeFeignClientImpl является реализацией интерфейса EmployeeFeignClient,
 * он содержит реализацию методов для отправки запросов в edo-repository через FeignClient.
 * <p>
 * Класс использует аннотацию @Component, которая позволяет Spring автоматически обнаруживать
 * наши пользовательские bean-компоненты.
 */
@Component
public class EmployeeFeignClientImpl implements EmployeeFeignClient {

    private Boolean isSecurityEnabled;

    /**
     * Объект интерфейса EmployeeFeignClient.
     */
    private final EmployeeFeignClient employeeFeignClient;

    /**
     * Конструктор класса EmployeeFeignClientImpl.
     */
    public EmployeeFeignClientImpl(@Value("${security.enabled:false}") Boolean isSecurityEnabled,
                                   EmployeeFeignClient employeeFeignClient) {
        this.isSecurityEnabled = isSecurityEnabled;
        this.employeeFeignClient = employeeFeignClient;
    }

    /**
     * Метод getAllEmployeeById получает список объектов EmployeeDto по заданным id.
     *
     * @param ids             Список идентификаторов сущностей.
     * @param notArchivedOnly Логическая переменная. При значении true поиск выполняется
     *                        только среди неархивированных сущностей.
     *                        При значении false - среди всех сущностей.
     * @return Список EmployeeDto, соответствующих сущностям с указанными id.
     * Если объекты с заданным id не найдены, метод возвращает пустой список.
     */
    @Override
    public List<EmployeeDto> getAllEmployeeById(List<Long> ids, boolean notArchivedOnly) {
        return employeeFeignClient.getAllEmployeeById(ids, notArchivedOnly);
    }

    /**
     * Метод saveEmployee сохраняет сущность сотрудника (Employee) в таблицу.
     *
     * @param employeeDto Сущность сотрудника для сохранения.
     * @return Объект класса EmployeeDto, соответствующий сохраненной сущности.
     */
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        return employeeFeignClient.saveEmployee(employeeDto);
    }

    /**
     * Метод getEmployeeById получает объект EmployeeDto по заданному id и дает возможность
     * поиска среди архивированных и неархивированных сущностей.
     *
     * @param id              id сущности.
     * @param notArchivedOnly Логическая переменная. При значении true поиск выполняется
     *                        только среди неархивированных сущностей.
     *                        При значении false - среди всех сущностей.
     * @return Объект класса EmployeeDto, соответствующий сущности с указанным id.
     * Если объект с заданным id не найден, метод возвращает null.
     */
    @Override
    public EmployeeDto getEmployeeById(Long id, boolean notArchivedOnly) {
        return employeeFeignClient.getEmployeeById(id, notArchivedOnly);
    }

    /**
     * Метод moveToArchiveEmployeeById перемещает сущность из таблицы employee в архив.
     *
     * @param id id сущности.
     */
    @Override
    public void moveToArchiveEmployeeById(Long id) {
        employeeFeignClient.moveToArchiveEmployeeById(id);
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
