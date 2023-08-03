package com.education.repository;

import com.education.entity.Facsimile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Ярослав Рябоконь
 * Интерфейс, наследующийся от JpaRepository для доступа к таблице Facsimile
 */
@Repository
public interface FacsimileRepository extends JpaRepository<Facsimile, Long> {
}
