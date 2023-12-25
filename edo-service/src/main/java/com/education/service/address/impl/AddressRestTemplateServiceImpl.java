package com.education.service.address.impl;

import com.education.model.dto.AddressDto;
import com.education.service.address.AddressService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.http.HttpHost;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Random;

/**
 * Сервис для контроллера, работает с AddressDto
 */
@Service
@RequiredArgsConstructor
@Log
public class AddressRestTemplateServiceImpl implements AddressService {

    /**
     * Клиент для отправки и получения запросов
     */
    @NonNull
    private RestTemplate template;

    /**
     * Клиент для получения instance
     */
    @NonNull
    private EurekaClient eurekaClient;

    /**
     * путь до рест контроллера репозитория
     */
    private static final String BASE_URL = "/api/repository/address";

    /**
     * get addresses By Id
     *
     * @param id Long
     * @return AddressDto
     */
    @Override
    public AddressDto getById(Long id) {
        URI uri = generateUri(this.getInstance(), id);
        var request = new RequestEntity<>(null, HttpMethod.GET, uri);

        return template.exchange(request, AddressDto.class).getBody();
    }

    /**
     * fetch Addressed by List
     *
     * @param idList List<Long>
     * @return List<AddressDto>
     */
    @Override
    public List<AddressDto> fetchAddressesList(List<Long> idList) {
        String lastPathComponent = "/findAll";
        URI uri = generateUri(this.getInstance(), lastPathComponent);
        var request = new RequestEntity<>(idList, HttpMethod.POST, uri);
        var myBean = new ParameterizedTypeReference<List<AddressDto>>() {};
        var response = template.exchange(request, myBean);

        return response.getBody();
    }

    /**
     * save Address in db
     *
     * @param address AddressDto
     * @return AddressDto
     */
    @Override
    public AddressDto save(AddressDto address) {
        String lastPathComponent = "/";
        URI uri = generateUri(this.getInstance(), lastPathComponent);
        var request = new RequestEntity<>(address, HttpMethod.POST, uri);

        return template.exchange(request, AddressDto.class).getBody();
    }

    /**
     * delete address from db
     *
     * @param id Long
     */
    @Override
    public void delete(Long id) {
        URI uri = generateUri(this.getInstance(), id);
        template.delete(uri);
    }

    /**
     * getInstance
     *
     * @return InstanceInfo
     */
    private InstanceInfo getInstance() {
        String serviceName = "edo-repository";
        Application app = eurekaClient.getApplication(serviceName);
        if (app == null) {
            log.warning("EurekaClient  не смогла достучаться до edo-repository");
        }
        return app.getInstances().get(new Random().nextInt(app.size()));
    }

    /**
     * generateUri
     *
     * @param instance InstanceInfo
     * @param id       Long
     * @return URI
     */
    private URI generateUri(InstanceInfo instance, Long id) {
        String lastPartComponent = "/{id}";
        return UriComponentsBuilder.fromPath(BASE_URL + lastPartComponent)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
    }

    /**
     * generateUri
     *
     * @param instance InstanceInfo
     * @param path     String
     * @return URI
     */
    private URI generateUri(InstanceInfo instance, String path) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
    }
}
