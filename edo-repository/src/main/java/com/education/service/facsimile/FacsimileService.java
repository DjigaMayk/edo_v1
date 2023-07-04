package com.education.service.facsimile;


import com.education.entity.Facsimile;

import java.util.Optional;

public interface FacsimileService {
    Optional<Facsimile> findById(Long id);
    Facsimile saveFacsimile(Facsimile facsimile);
    void moveInArchive(Long id, boolean isArchived);
}
