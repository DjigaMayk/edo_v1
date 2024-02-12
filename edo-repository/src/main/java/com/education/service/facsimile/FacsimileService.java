package com.education.service.facsimile;

import com.education.entity.Facsimile;
import com.education.model.dto.FacsimileDto;
import com.education.service.BaseInterface;

import java.util.Optional;

public interface FacsimileService extends BaseInterface<FacsimileDto> {

    Optional<Facsimile> findById(Long id);

    Facsimile saveFacsimile(Facsimile facsimile);

    void moveInArchive(Long id, boolean isArchived);

    Facsimile findFacsimileByEmployeeId(Long id);
}
