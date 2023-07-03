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
    @Query(value = "UPDATE facsimile SET is_archived = true WHERE id = :id", nativeQuery = true)
    void moveToArchive(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE facsimile SET is_archived = false WHERE id = :id", nativeQuery = true)
    void moveFromArchive(@Param("id") Long id);
}
