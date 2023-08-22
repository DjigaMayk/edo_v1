package com.education.client.feign.nomenclature.impl;

import com.education.client.feign.nomenclature.NomenclatureFeignClient;
import com.education.model.dto.NomenclatureDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс NomenclatureFeignClientImpl является реализацией интерфейса NomenclatureFeignClient,
 * он содержит реализацию методов для отправки запросов в edo-repository через FeignClient.
 * <p>
 * Класс использует аннотацию @Component, которая позволяет Spring автоматически обнаруживать
 * наши пользовательские bean-компоненты.
 */
@Component
public class NomenclatureFeignClientImpl implements NomenclatureFeignClient {

    /**
     * Объект интерфейса NomenclatureFeignClient.
     */
    private final NomenclatureFeignClient nomenclatureFeignClient;

    /**
     * Конструктор класса NomenclatureFeignClientImpl.
     */
    public NomenclatureFeignClientImpl(NomenclatureFeignClient nomenclatureFeignClient) {
        this.nomenclatureFeignClient = nomenclatureFeignClient;
    }

    /**
     * Отправляет запрос на сохранение номенклатуры.
     *
     * @param nomenclature NomenclatureDto
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto saveNomenclature(NomenclatureDto nomenclature) {
        return nomenclatureFeignClient.saveNomenclature(nomenclature);
    }

    /**
     * Отправляет запрос для предоставления NomenclatureDto номенклатуры по id.
     *
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findByIdNomenclature(Long id) {
        return nomenclatureFeignClient.findByIdNomenclature(id);
    }

    /**
     * Отправляет запрос для предоставления списка NomenclatureDto номенклатур по id.
     *
     * @param ids список идентификаторов
     * @return список NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllByIdNomenclature(List<Long> ids) {
        return nomenclatureFeignClient.findAllByIdNomenclature(ids);
    }

    /**
     * Отправляет запрос для предоставления не архивированной NomenclatureDto номенклатуры по id.
     *
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findByIdNotArchivedNomenclature(Long id) {
        return nomenclatureFeignClient.findByIdNotArchivedNomenclature(id);
    }

    /**
     * Отправляет запрос для предоставления списка не архивированных NomenclatureDto номенклатур по id.
     *
     * @param ids список идентификаторов
     * @return список NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllByIdNotArchivedNomenclature(List<Long> ids) {
        return nomenclatureFeignClient.findAllByIdNotArchivedNomenclature(ids);
    }

    /**
     * Отправляет запрос для перевода номенклатуры в архив.
     *
     * @param id Long
     */
    @Override
    public void moveToArchiveNomenclature(Long id) {
        nomenclatureFeignClient.moveToArchiveNomenclature(id);
    }

    /**
     * Отправляет запрос для поиска NomenclatureDto номенклатур по индексу.
     *
     * @param index индекс
     * @return список NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findByIndex(String index) {
        return nomenclatureFeignClient.findByIndex(index);
    }
}
