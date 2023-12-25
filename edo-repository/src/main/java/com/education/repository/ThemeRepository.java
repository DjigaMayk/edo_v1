package com.education.repository;

import com.education.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для тем обращения
 */
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    /**
     * Выдает лист тем, у которых архивная дата отсутствует в соответствии со списком переданных id
     */
    @Query("select th from Theme th where th.id in (:ids) and th.archivedDate is null")
    List<Theme> findAllNotArchived(@Param("ids") List<Long> ids);

    /**
     * Выдает тему по запрошенному id если у темы отсутствует дата архивации
     */
    Theme findByIdAndArchivedDateIsNull(Long id);

    /**
     * Проставляет для темы архивную дату (current_timestamp)
     */
    @Modifying
    @Query(value = "UPDATE theme SET archived_date = current_timestamp WHERE id = :theme_id", nativeQuery = true)
    Integer moveToArchive(@Param("theme_id") Long id);
}