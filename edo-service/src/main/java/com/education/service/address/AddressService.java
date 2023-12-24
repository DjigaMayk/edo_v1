package com.education.service.address;

import com.education.model.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto getById(Long id);

    List<AddressDto> fetchAddressesList(List<Long> idList);

    AddressDto save(AddressDto address);

    void delete(Long id);
}
