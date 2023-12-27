package com.education.service.Resolution;

import com.education.feign.feign_resolution.ResolutionFeignClient;
import com.education.model.dto.ResolutionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    private final ResolutionFeignClient resolutionFeignClient;

    @Override
    public ResolutionDto save(ResolutionDto resolutionDto) {
        return resolutionFeignClient.save(resolutionDto);
    }

    @Override
    public  ResolutionDto findById(Long id) {return resolutionFeignClient.findById(id);}

    @Override
    public List<ResolutionDto> findAllByAppealIdNotArchived(Long appealId) {return resolutionFeignClient.findAllByAppealIdNotArchived(appealId); }
}
