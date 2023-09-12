package com.education.service.resolution;

import com.education.entity.Resolution;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResolutionService {
    Resolution save(Resolution resolution);

    void moveToArchive(Long id);

    void removeFromArchive(@Param("id") Long id);

    Resolution findById(Long id);

    List<Resolution> findAllById(Iterable<Long> ids);

    Resolution findByIdNotArchived(Long id);

    List<Resolution> findAllByIdNotArchived(Iterable<Long> ids);

    List<Resolution> findAllByAppealIdNotArchived(Long appealId);

    Boolean isDraft(@Param("id") Long id);
}
