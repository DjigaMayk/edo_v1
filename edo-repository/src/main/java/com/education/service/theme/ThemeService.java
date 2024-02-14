package com.education.service.theme;

import com.education.model.dto.ThemeDto;
import com.education.service.BaseInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThemeService extends BaseInterface<ThemeDto> {

    ThemeDto save(ThemeDto themeDto);

    Integer moveToArchive(Long id);

    ThemeDto findById(Long id);

    List<ThemeDto> findAllById(List<Long> ids);

    ThemeDto findByIdNotArchived(Long id);

    List<ThemeDto> findAllByIdNotArchived(List<Long> ids);
}