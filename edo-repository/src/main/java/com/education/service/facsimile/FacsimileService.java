package com.education.service.facsimile;

import com.education.entity.Facsimile;
import org.springframework.stereotype.Service;

@Service
public interface FacsimileService {
    Facsimile findById(Long id);
    Facsimile findFacsimileByEmployeeId(Long id);
}
