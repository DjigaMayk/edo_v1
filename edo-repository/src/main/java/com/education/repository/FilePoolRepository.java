package com.education.repository;

import com.education.entity.FilePool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilePoolRepository extends JpaRepository<FilePool, Long> {
    /**
     * Метод предоставляет не заархивированное хранилище файлов по id
     */
    @Query(value = "SELECT n FROM FilePool n WHERE n.id = :id AND n.archivedDate is null")
    Optional<FilePool> findByIdNotArchived(@Param("id") Long id);

    /**
     * Метод предоставляет список не заархивированных номенклатур по id
     */
    @Query(value = "SELECT n FROM FilePool n WHERE n.id in :list AND n.archivedDate is null")
    List<FilePool> findAllByIdNotArchived(@Param("list") Iterable<Long> list);

    /**
     * Метод переводит в архив номенклатуру присваивая значение даты архивации
     */
    @Modifying
    @Query(value = "UPDATE file_pool SET archived_date = CURRENT_TIMESTAMP WHERE id = :id", nativeQuery = true)
    void moveToArchive(Long id);
}
