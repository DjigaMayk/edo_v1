package com.education.service.nomenclature.impl;

import com.education.client.feign.nomenclature.NomenclatureFeignClient;
import com.education.model.dto.NomenclatureDto;
import com.education.service.nomenclature.NomenclatureFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class NomenclatureFeignServiceImpl implements NomenclatureFeignService {

    private final NomenclatureFeignClient nomenclatureFeignClient;

    /**
     * Сохраняет номенклатуру
     *
     * @param nomenclature NomenclatureDto
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto save(NomenclatureDto nomenclature) {
        return nomenclatureFeignClient.saveNomenclature(nomenclature);
    }

    /**
     * Переводит номенклатуру в архив
     *
     * @param id Long
     */
    @Override
    public void moveToArchive(Long id) {
        nomenclatureFeignClient.moveToArchiveNomenclature(id);
    }

    /**
     * Предоставляет NomenclatureDto номенклатуры по id
     *
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findById(Long id) {
        return nomenclatureFeignClient.findByIdNomenclature(id);
    }

    /**
     * Предоставляет список NomenclatureDto номенклатур по id
     *
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllById(List<Long> list) {
        return nomenclatureFeignClient.findAllByIdNomenclature(list);
    }

    /**
     * Предоставляет не заархивированное NomenclatureDto номенклатуры по id
     *
     * @param id Long
     * @return NomenclatureDto
     */
    @Override
    public NomenclatureDto findByIdNotArchived(Long id) {
        return nomenclatureFeignClient.findByIdNotArchivedNomenclature(id);
    }

    /**
     * Предоставляет список не заархивированных NomenclatureDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findAllByIdNotArchived(List<Long> list) {
        return nomenclatureFeignClient.findAllByIdNotArchivedNomenclature(list);
    }

    /**
     * Предоставляет список номенклатур из БД по индексу
     *
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findByIndex(String index) {
        return index.length() < 2 ? null : nomenclatureFeignClient.findByIndex(index);
    }

    /**
     * Преобразует номер обращения из шаблона номенклатуры
     *
     * @param nomenclatureDto NomenclatureDto
     * @return String of NomenclatureDto
     */
    @Override
    public String getNumberFromTemplate(NomenclatureDto nomenclatureDto) {
        final String TEMPLATE = "%ЧИС%ГОД-%ЗНАЧ/2";
        var temp = nomenclatureDto.getTemplate();
        if (temp == null) {
            temp = TEMPLATE;
        }
        String currentValue = nomenclatureDto.getCurrentValue().toString();
        nomenclatureDto.setCurrentValue(Long.parseLong(currentValue) + 1);
        nomenclatureFeignClient.saveNomenclature(nomenclatureDto);
        String year = String.format("%02d", Calendar.getInstance().get(Calendar.YEAR) % 100);
        String day = String.format("%02d", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        return temp
//  убирает больше двух знаков "%" подряд, оставляя один
                .replaceAll("%{2,}", "%")
//  заменяет число дня
                .replaceAll("%чис|%ЧИС|%число|%ЧИСЛО", day)
//  заменяет год
                .replaceAll("%год|%ГОД", year)
//  заменяет значение
                .replaceAll("%знач|%ЗНАЧ|%значение|%ЗНАЧЕНИЕ", StringUtils.isEmpty(currentValue) ? "" : currentValue)
//  убирает проценты с флагом
                .replaceAll("%[\\W][^(а-яА-Я)]", "")
//  убирает больше двух знаков "-" подряд, оставляя один
                .replaceAll("-{2,}", "-")
//  убирает больше двух знаков "/" подряд, оставляя один
                .replaceAll("/{2,}", "/")
//  убирает знаки "-" и "/" в начале и в конце
                .replaceAll("(^[-/]+)|([-/]*$)", "");
    }
}
