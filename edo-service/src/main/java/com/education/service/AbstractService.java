package com.education.service;

import com.education.feign.AbstractFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Набор стандартных методов обращения к FeignClient для использования в сервисах.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public abstract class AbstractService<FeignClient extends AbstractFeign<Dto>, Dto> {

    protected final FeignClient feignClient;

    public Dto getById(Long id) {
        return feignClient.getById(id);
    }

    public List<Dto> findAll() {
        return feignClient.findAll();
    }

    public Dto save(Dto dto) {
        return feignClient.save(dto);
    }

    public void delete(Long id) {
        feignClient.delete(id);
    }
}


