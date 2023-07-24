//package com.education.service.department;
//
//import com.education.model.dto.DepartmentDto;
//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.EurekaClient;
//import com.netflix.discovery.shared.Application;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.java.Log;
//import org.apache.http.HttpHost;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.RequestEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;
//import java.util.Random;
//
///**
// * Сервис для контроллера, работает с DepartmentDto
// */
//@Service
//@RequiredArgsConstructor
//@Log
//public class DepartmentRestTemplateServiceImpl implements DepartmentService {
//
//    /**
//     * Клиент для отправки и получения запросов
//     */
//    private final RestTemplate template;
//
//    /**
//     * Клиент для получения instance
//     */
//    private final EurekaClient eurekaClient;
//
//    /**
//     * путь до рест контроллера репозитория
//     */
//    private static final String BASE_URL = "/api/repository/department";
//
//    /**
//     * Название микросервиса, к которому мы пытаемся получить доступ
//     */
//    private final String serviceName = "edo-repository";
//
//    @Override
//    public DepartmentDto save(DepartmentDto obj) {
//        String lastPathComponent = "/";
//        URI uri = generateUri(this.getInstance(), lastPathComponent);
//        var request = new RequestEntity<>(obj, HttpMethod.POST, uri);
//
//        return template.exchange(request, DepartmentDto.class).getBody();
//    }
//
//    @Override
//    public void moveToArchive(Long id) {
//        URI uri = generateUri(this.getInstance(), id);
//        template.delete(uri);
//    }
//
//    @Override
//    public DepartmentDto findById(Long id) {
//        URI uri = generateUri(this.getInstance(), id);
//        var request = new RequestEntity<>(null, HttpMethod.GET, uri);
//        var response = template.exchange(request, DepartmentDto.class);
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            log.warning("Can't get the department");
//        }
//        return response.getBody();
//    }
//
//    @Override
//    public DepartmentDto findByIdNotArchived(Long id) {
//        String lastPathComponent = "/notArchived/" + id;
//        URI uri = generateUri(this.getInstance(), lastPathComponent);
//        var request = new RequestEntity<>(null, HttpMethod.GET, uri);
//        var response = template.exchange(request, DepartmentDto.class);
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            log.warning("Can't get the department");
//        }
//
//        return response.getBody();
//    }
//
//    @Override
//    public List<DepartmentDto> findAllById(List<Long> ids) {
//        String lastPathComponent = "/findAll";
//        URI uri = generateUri(this.getInstance(), lastPathComponent);
//        var request = new RequestEntity<>(ids, HttpMethod.POST, uri);
//        var myBean = new ParameterizedTypeReference<List<DepartmentDto>>() {
//        };
//        var response = template.exchange(request, myBean);
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            log.warning("Can't get the department");
//        }
//        return response.getBody();
//    }
//
//    @Override
//    public List<DepartmentDto> findAllByIdNotArchived(List<Long> ids) {
//        String lastPathComponent = "/findAll/notArchived";
//        URI uri = generateUri(this.getInstance(), lastPathComponent);
//        var request = new RequestEntity<>(ids, HttpMethod.POST, uri);
//        var myBean = new ParameterizedTypeReference<List<DepartmentDto>>() {
//        };
//        var response = template.exchange(request, myBean);
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new RuntimeException("Can't get the theme");
//        }
//        return response.getBody();
//    }
//
//    /**
//     * getInstance
//     *
//     * @return InstanceInfo
//     */
//    private InstanceInfo getInstance() {
//        Application app = eurekaClient.getApplication(serviceName);
//        if (app == null) {
//            log.warning("EurekaClient  не смогла достучаться до edo-repository");
//        }
//        return app.getInstances().get(new Random().nextInt(app.size()));
//    }
//
//    /**
//     * generateUri
//     *
//     * @param instance InstanceInfo
//     * @param id       Long
//     * @return URI
//     */
//    private URI generateUri(InstanceInfo instance, Long id) {
//        String lastPartComponent = "/{id}";
//        return UriComponentsBuilder.fromPath(BASE_URL + lastPartComponent)
//                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
//                .host(instance.getHostName())
//                .port(instance.getPort())
//                .buildAndExpand(id)
//                .toUri();
//    }
//
//    /**
//     * generateUri
//     *
//     * @param instance InstanceInfo
//     * @param path     String
//     * @return URI
//     */
//    private URI generateUri(InstanceInfo instance, String path) {
//        return UriComponentsBuilder.fromPath(BASE_URL + path)
//                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
//                .host(instance.getHostName())
//                .port(instance.getPort())
//                .build()
//                .toUri();
//    }
//}
