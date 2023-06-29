package com.education.service.facsimile.impl;

import com.education.model.dto.*;
import com.education.service.facsimile.FacsimileService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.util.List;

import static org.apache.commons.lang.StringUtils.EMPTY;

/**
 * @author Никита Бадеев
 * Service для сохранения факсимиле
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class FacsimileServiceImpl implements FacsimileService {

    private final RestTemplate TEMPLATE;

    private final EurekaClient EUREKA_CLIENT;

    private final String BASE_URL = "/api/service/facsimile";

    private final String SERVICE_NAME = "edo-service";


    @Override
    public FacsimileDTO saveFacsimileEntity(String jsonFile) {
        InstanceInfo instanceInfo = getInstance();
        return null;
    }

    @Override
    public FilePoolDto saveFacsimile(MultipartFile multipartFile) {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("facsimile", multipartFile.getResource());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        return TEMPLATE.exchange(getDefaultUriComponentBuilder(BASE_URL)
                .build()
                .toUri(), HttpMethod.POST, requestEntity, FilePoolDto.class).getBody();
    }

    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));
        log.info(instance.getPort());
        return instance;
    }

    private UriComponentsBuilder getDefaultUriComponentBuilder(String path) {
        InstanceInfo instanceInfo = getInstance();
        return UriComponentsBuilder
                .fromPath(path)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort());
    }
}
