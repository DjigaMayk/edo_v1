package com.education.utils.sorterResolutions;

import com.education.model.dto.ResolutionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для сортировки резолюций.
 * Содержит методы для сортировки списка резолюций для построения дерева вложенности.
 *
 * @author Соколянский Юрий.
 */
public class SorterResolutions {

    public static List<ResolutionDto> sortResolutions(List<ResolutionDto> resolutions) {
        Map<Long, List<ResolutionDto>> resolutionMap = new HashMap<>();
        List<ResolutionDto> result = new ArrayList<>();

        // Группируем резолюции по parentId
        for (ResolutionDto resolution : resolutions) {
            if(resolution.getParentResolution() != null){
                resolutionMap.computeIfAbsent(resolution
                        .getParentResolution().getId(), k -> new ArrayList<>()).add(resolution);
            } else {
                if (resolutionMap.get(null) != null) {
                    resolutionMap.get(null).add(resolution);
                } else {
                    resolutionMap
                            .computeIfAbsent(null, k -> new ArrayList<>())
                            .add(resolution);
                }
            }
        }

        // Сортируем и добавляем резолюции в результат
        sortAndAddResolutions(null, resolutionMap, result);

        return result;
    }

    private static void sortAndAddResolutions(Long parentId, Map<Long, List<ResolutionDto>> resolutionMap, List<ResolutionDto> result) {
        List<ResolutionDto> resolutions = resolutionMap.get(parentId);

        if (resolutions != null) {
            resolutions.sort((r1, r2) -> Long.compare(r1.getId(), r2.getId()));

            for (ResolutionDto resolution : resolutions) {
                result.add(resolution);
                sortAndAddResolutions(resolution.getId(), resolutionMap, result);
            }
        }
    }
}
