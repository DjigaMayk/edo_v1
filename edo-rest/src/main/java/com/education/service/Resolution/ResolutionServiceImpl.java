package com.education.service.Resolution;

import com.education.feign.feign_resolution.ResolutionFeignClient;
import com.education.model.dto.ResolutionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService{

    private final ResolutionFeignClient resolutionFeignClient;

    @Override
    public ResolutionDto save(ResolutionDto resolutionDto) {
        return resolutionFeignClient.save(resolutionDto);
    }
}
