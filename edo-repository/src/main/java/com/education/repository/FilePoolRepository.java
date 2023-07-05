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
     *
     * @param id Long
     * @return Optional of Nomenclature
     */
    @Query(value = "SELECT f FROM FilePool f WHERE f.id = :id AND f.archivedDate is null")
    Optional<FilePool> findByIdNotArchived(@Param("id") Long id);

    /**
     * Метод предоставляет список не заархивированное хранилище файлов по id
     *
     * @param list List of id
     * @return List of Nomenclature
     */
    @Query(value = "SELECT f FROM FilePool f WHERE f.id in :list AND f.archivedDate is null")
    List<FilePool> findAllByIdNotArchived(@Param("list") Iterable<Long> list);

    /**
     * Метод переводит в архив номенклатуру присваивая значение даты архивации
     */
    @Modifying
    @Query(value = "UPDATE file_pool SET archived_date = CASE WHEN :archived = true THEN CURRENT_TIMESTAMP END WHERE id = :id", nativeQuery = true)
    void moveToArchive(@Param("id") Long id, @Param("archived") boolean isArchived);
}

