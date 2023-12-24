package com.education.service.appeal;

import com.education.feign.feign_appeal.AppealFeignClient;
import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.education.model.enumEntity.EnumAppealStatus.NEW;

@Log4j2
@Service
@RequiredArgsConstructor
public class CreatingAppealServiceImpl implements CreatingAppealService {

    private final RestTemplate TEMPLATE;
    private final EurekaClient EUREKA_CLIENT;
    private final AppealFeignClient appealFeignClient;

    private final String BASE_URL = "/api/service/appeal";

    private final String SERVICE_NAME = "edo-service";

    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));
        log.info(instance.getPort());
        return instance;
    }

    private String getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        return UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .build().toString();
    }

    @Override
    public AppealDto createAppeal(AppealDto appealDto) {
        appealDto.setAppealStatus(NEW);
        return appealFeignClient.createAppeal(appealDto);
    }

    @Override
    public AppealDto editAppeal(AppealDto appealDto) {
        AppealDto ifAppealExists = findById(appealDto.getId());
        if (ifAppealExists != null) {
            return createAppeal(appealDto);
        }
        return null;
    }

    @Override
    public List<AppealAbbreviatedDto> findAllByIdEmployee(Long first, Long amount) {
        return appealFeignClient.findAllByIdEmployee(first, amount);
    }

    @Override
    public AppealDto findById(Long id) {
        try {
            return appealFeignClient.findById(id);
        } catch (FeignException.FeignClientException.NotFound e) {
            return null;
        }
    }
}