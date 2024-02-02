package com.education.repository;

import com.education.entity.Resolution;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Long> {

    @EntityGraph(attributePaths = {"creator", "signer", "executors", "curator"})
    Optional<Resolution> findById(Long id);

    @EntityGraph(attributePaths = {"creator", "signer", "executors", "curator"})
    List<Resolution> findAllById(Iterable<Long> ids);

    @Modifying
    @Query(value = "UPDATE resolution SET archived_date = current_timestamp where id = :id",
            nativeQuery = true)
    void moveToArchive(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE resolution SET archived_date = null where id = :id",
            nativeQuery = true)
    void removeFromArchive(@Param("id") Long id);

    @Query("select r from Resolution r where r.id = :id and r.archivedDate is null")
    @EntityGraph(attributePaths = {"creator", "signer", "executors", "curator"})
    Optional<Resolution> findByIdNotArchived(@Param("id") Long id);

    @Query(value = "select r from Resolution r where r.id in :ids and r.archivedDate is null ")
    @EntityGraph(attributePaths = {"creator", "signer", "executors", "curator"})
    List<Resolution> findAllByIdNotArchived(@Param("ids") Iterable<Long> ids);

    @Query(nativeQuery = true,
            value = "select r.* from resolution r " +
                    "left join appeal_question aq on r.question_id = aq.question_id " +
                    "left join appeal a on aq.appeal_id = a.id where a.id = :appealId and r.archived_date is null")
    List<Resolution> findAllByAppealIdNotArchived(@Param("appealId") Long appealId);

    @Query(nativeQuery = true,
            value = "SELECT is_draft from resolution where id = :id")
    Optional<Boolean> isDraft(@Param("id") Long id);

    @Query("SELECT r FROM Resolution r")
    @EntityGraph(attributePaths = {"creator", "signer", "executors", "curator", "question"})
    List<Resolution> findAllResolution();

    @Query("SELECT r FROM Resolution r WHERE r.archivedDate IS NULL")
    @EntityGraph(attributePaths = {"creator", "signer", "executors", "curator", "question"})
    List<Resolution> findAllResolutionNonArchived();

    @Query("SELECT r FROM Resolution r WHERE r.archivedDate IS NOT NULL")
    @EntityGraph(attributePaths = {"creator", "signer", "executors", "curator", "question"})
    List<Resolution> findAllResolutionArchived();

    @Query("SELECT DISTINCT r FROM Resolution r LEFT JOIN FETCH r.deadlines WHERE r.id in :ids")
    List<Resolution> findAllWithDeadline(@Param("ids") Iterable<Long> ids);
}
