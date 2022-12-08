package com.goit.homeworkspring.service;

import com.goit.homeworkspring.model.dto.ManufacturersDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ManufacturersService {

    ManufacturersDto saveManufacturer(ManufacturersDto manufacturer);

    List<ManufacturersDto> findByName(String name);

    Optional<ManufacturersDto> findById(UUID id);

    Set<ManufacturersDto> findAll();

    void delete(ManufacturersDto manufacturer);

    ManufacturersDto update(ManufacturersDto manufacturer);
}
