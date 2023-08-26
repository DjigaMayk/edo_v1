package com.education.service.region;

import com.education.model.dto.RegionDto;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface RegionService {

    RegionDto save(RegionDto region);

    RegionDto findById(Long id);

    List<RegionDto> findAll();

    HttpStatus deleteByIdRegion(Long id);

    InstanceInfo getInstance();

}
