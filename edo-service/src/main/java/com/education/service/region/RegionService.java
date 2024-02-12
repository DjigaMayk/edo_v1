package com.education.service.region;

import com.education.model.dto.RegionDto;
import com.education.service.BaseInterface;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface RegionService extends BaseInterface<RegionDto> {

    RegionDto save(RegionDto region);

    RegionDto findById(Long id);

    List<RegionDto> findAll();

    HttpStatus deleteByIdRegion(Long id);

    InstanceInfo getInstance();

}
