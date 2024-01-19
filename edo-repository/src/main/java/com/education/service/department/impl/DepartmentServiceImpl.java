package com.education.service.department.impl;

import com.education.Utils.QuestionUtil;
import com.education.entity.Department;
import com.education.repository.DepartmentRepository;
import com.education.service.address.AddressService;
import com.education.service.department.DepartmentService;
import com.education.util.Mapper.impl.AddressMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Department save(@NotNull Department obj) {
        if (obj.getAddress() == null) {
            return null;
        } else {
            addressService.save(addressMapper.toDto(obj.getAddress()));
            return departmentRepository.saveAndFlush(obj);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        departmentRepository.moveToArchive(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Department> findAllById(List<Long> ids) {
        return departmentRepository.findAllById(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Optional<Department> findByIdNotArchived(Long id) {
        return departmentRepository.findDepartmentByIdAndArchivedDateIsNull(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Department> findAllByIdNotArchived(List<Long> ids) { // test
        List<Department> result = departmentRepository.findAllById(ids);
        result.removeIf(element -> element.getArchivedDate() != null);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Optional<Department> findByFullName(String fullName) {
        if (fullName.matches("[а-яА-Я]")) {
            return departmentRepository.findByFullName(fullName);
        }
        String translitDepartment = QuestionUtil.textTransformer(fullName);
        return departmentRepository.findByFullName(translitDepartment);
    }
}
