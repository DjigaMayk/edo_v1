package com.education.repository;

import com.education.entity.Facsimile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacsimileRepository extends JpaRepository<Facsimile, Long> {
    @Modifying
    @Query(value = "UPDATE facsimile SET is_archived = :isArchived WHERE id = :id", nativeQuery = true)
    void moveInArchive(@Param("id") Long id,@Param("isArchived") boolean isArchived);

    /**
     * Метод, производящий поиск в таблице сущности Facsimile с заданным employee_id,
     * не находящейся в архиве
     * @param id id сущности
     * @return объект сущности Employee
     */

    @Query("SELECT fac FROM Facsimile fac LEFT JOIN FETCH fac.file WHERE fac.employee.id = :id")
    Facsimile findFacsimileByEmployeeId(@Param("id") Long id);
}
