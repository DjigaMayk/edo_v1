package com.education.service.resolution;

import com.education.model.dto.ResolutionDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ResolutionService {
    ResolutionDto save(ResolutionDto resolution);

    void moveToArchive(Long id);

    void removeFromArchive(Long id);

    ResolutionDto findById(Long id);

    List<ResolutionDto> findAllById(Iterable<Long> ids);

    ResolutionDto findByIdNotArchived(Long id);

    List<ResolutionDto> findAllByIdNotArchived(Iterable<Long> ids);

    List<ResolutionDto> findAllByAppealIdNotArchived(Long appealId);

    List<ResolutionDto> findAllWithFilterArchived(@Nullable String filter);
}
