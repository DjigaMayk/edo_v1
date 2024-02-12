package com.education.service.region;

import com.education.entity.Region;
import com.education.model.dto.RegionDto;
import com.education.service.BaseInterface;

import java.util.List;
import java.util.Optional;

public interface RegionService extends BaseInterface<RegionDto> {

    RegionDto save(RegionDto region);

    Optional<Region> findById(long id);

    List<RegionDto> findAll();

    void delete (long id);
}
