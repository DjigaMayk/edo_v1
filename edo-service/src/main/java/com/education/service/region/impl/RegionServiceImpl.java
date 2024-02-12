package com.education.service.region.impl;

import com.education.feign.feign_region.RegionFeignService;
import com.education.model.dto.RegionDto;
import com.education.service.AbstractService;
import com.education.service.region.RegionService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RegionServiceImpl extends AbstractService<RegionFeignService, RegionDto> implements RegionService {

    private EurekaClient client;

    private final RegionFeignService regionFeignService;

    public RegionServiceImpl(RegionFeignService regionFeignService, EurekaClient client, RegionFeignService regionFeignService1) {
        super(regionFeignService);
        this.client = client;
        this.regionFeignService = regionFeignService1;
    }

    /**
     * Метод для сохранения объекта region в БД.
     *
     * @param region
     */
    @Override
    public RegionDto save(RegionDto region) {
        var regionDto = regionFeignService.saveRegion(region);
        if (regionDto == null) {
            throw new RuntimeException("Регион не сохранен");
        }

        return regionDto;
    }

    /**
     * Метод для поиска обращения Region по id.
     *
     * @param id
     * @return RegionDto
     */
    @Override
    public RegionDto findById(Long id) {
        var regionDto = regionFeignService.findByIdRegion(id);
        if (regionDto == null) {
            throw new RuntimeException("Регион с таким id не найден");
        }

        return regionDto;
    }

    /**
     * Метод получения списка регионов
     *
     * @return List<RegionDto>
     */
    @Override
    public List<RegionDto> findAll() {
        return regionFeignService.findAll();
    }

    /**
     * Метод для удаления региона по id
     *
     * @param id
     * @return HttpStatus
     */
    @Override
    public HttpStatus deleteByIdRegion(Long id) {
        var responseEntity = regionFeignService.deleteByIdRegion(id);
        if (responseEntity.getStatusCode().is4xxClientError()) {
            throw new RuntimeException("Попытка удалить регион с несуществующим id");
        }

        return HttpStatus.OK;
    }

    /**
     * Метод для получения Instance
     *
     * @return InstanceInfo
     */
    @Override
    public InstanceInfo getInstance() {
        String SERVICE_NAME = "edo-repository";
        List<InstanceInfo> instances
                = client.getApplication(SERVICE_NAME).getInstances();

        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));

        log.info(instance.getPort());

        return instance;
    }
}
