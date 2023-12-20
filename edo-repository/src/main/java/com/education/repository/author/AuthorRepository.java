package com.education.repository.author;

import com.education.entity.Author;
import com.education.model.dto.AuthorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для сущности Автор
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

    @Query("SELECT aut FROM Author aut " +
            "WHERE LOWER(concat(aut.lastName,' ', aut.firstName,' ', aut.middleName)) " +
            "LIKE concat(LOWER(:fio) , '%') ORDER BY aut.lastName")
    List<AuthorDto> findAuthorByFIO(@Param("fio") String fio);
}
