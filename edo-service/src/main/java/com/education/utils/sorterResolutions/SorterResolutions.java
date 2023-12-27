package com.education.utils.sorterResolutions;

import com.education.model.dto.ResolutionDto;

import java.util.*;


/**
 * Класс для сортировки резолюций.
 * Содержит методы для сортировки списка резолюций для построения дерева вложенности.
 *
 * @author Соколянский Юрий.
 */
public class SorterResolutions {

    public static List<ResolutionDto> sortResolutions(List<ResolutionDto> resolutions) {
        Comparator<ResolutionDto> comparator = Comparator.comparing(ResolutionDto::getCreationDate);

        resolutions.sort(comparator);

        for (int i = 0; i < resolutions.size(); i++) {
            resolutions.get(i).setSerialNumber(i);
        }

        return resolutions;
    }

}
