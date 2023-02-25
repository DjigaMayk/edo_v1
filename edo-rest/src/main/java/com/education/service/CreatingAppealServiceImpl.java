package com.education.service;

import com.education.model.dto.AppealDto;
import com.education.model.enumEntity.EnumAppealStatus;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CreatingAppealServiceImpl implements CreatingAppealService {

    private final RestTemplate TEMPLATE;

    private final EurekaClient EUREKA_CLIENT;
    private final EnumAppealStatus STATUS_FOR_NEW_APPEAL = EnumAppealStatus.NEW;

    private final String BASE_URL = "/api/service/appeal";

    private final String SERVICE_NAME = "edo-service";

    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));

        log.info(instance.getPort());

        return instance;
    }

    private URI getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        URI uri = UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .buildAndExpand(pathVariable)
                .toUri();
        log.info(uri.toString());
        return uri;
    }

    @Override
    public AppealDto createAppeal(AppealDto appealDto) {
        InstanceInfo instanceInfo = getInstance();
        appealDto.setAppealStatus(STATUS_FOR_NEW_APPEAL);
        var request = new RequestEntity(appealDto, HttpMethod.POST, getURIByInstance(instanceInfo, ""));

        var response = TEMPLATE.exchange(request, AppealDto.class);

        return response.getBody();
    }
}