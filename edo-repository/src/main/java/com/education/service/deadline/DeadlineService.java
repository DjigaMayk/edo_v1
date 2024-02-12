package com.education.service.deadline;

import com.education.model.dto.DeadlineDto;
import com.education.service.BaseInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeadlineService extends BaseInterface<DeadlineDto> {

    DeadlineDto save(DeadlineDto deadlineDto);

    DeadlineDto update(DeadlineDto deadlineDto);

    void delete(Long id);

    DeadlineDto findById(Long id);

    List<DeadlineDto> findAllById(List<Long> ids);

}
