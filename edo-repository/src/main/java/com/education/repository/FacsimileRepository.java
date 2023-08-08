package com.education.repository;

import com.education.entity.Facsimile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * @author Ярослав Рябоконь
 * Интерфейс, наследующийся от JpaRepository для доступа к таблице Facsimile
 */
@Repository
public interface FacsimileRepository extends JpaRepository<Facsimile, Long> {

    /**
     * Метод, производящий поиск в таблице сущности Facsimile с заданным employee_id,
     * не находящейся в архиве
     * @param id id сущности
     * @return объект сущности Employee
     */

    @Query("SELECT fac FROM Facsimile fac LEFT JOIN FETCH fac.file WHERE fac.employee.id = :id")
    Facsimile findFacsimileByEmployeeId(@Param("id") Long id);
}
