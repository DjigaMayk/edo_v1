package com.education.service.facsimile.impl;

import com.education.client.FacsimileRestTemplateClient;
import com.education.model.dto.FacsimileDto;
import com.education.service.facsimile.FacsimileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FacsimileServiceImpl implements FacsimileService {
    private final FacsimileRestTemplateClient facsimileRestTemplateClient;
    @Override
    public FacsimileDto findById(Long id) {
        return facsimileRestTemplateClient.getFacsimileById(id);
    }
}
