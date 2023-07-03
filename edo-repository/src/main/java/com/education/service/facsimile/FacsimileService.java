package com.education.service.facsimile;


import com.education.entity.Facsimile;

import java.util.Optional;

public interface FacsimileService {
    void moveToArchive(Long id);
    Optional<Facsimile> findById(Long id);
    Facsimile saveFacsimile(Facsimile facsimile);
}
