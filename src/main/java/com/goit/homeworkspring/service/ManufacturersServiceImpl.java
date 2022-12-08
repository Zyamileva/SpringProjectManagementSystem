package com.goit.homeworkspring.service;

import com.goit.homeworkspring.model.dao.ManufacturersDao;
import com.goit.homeworkspring.model.dto.ManufacturersDto;
import com.goit.homeworkspring.repository.ManufacturesRepository;
import com.goit.homeworkspring.service.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufacturersServiceImpl implements ManufacturersService {
    private final ManufacturesRepository manufacturesRepository;
    private final Converter<ManufacturersDto, ManufacturersDao> converterManufacturer;

    @Override
    public ManufacturersDto saveManufacturer(ManufacturersDto manufacturer) {
        ManufacturersDao dao = converterManufacturer.to(manufacturer);
        return converterManufacturer.from(manufacturesRepository.save(dao));
    }

    @Override
    public ManufacturersDto update(ManufacturersDto manufacturer) {
        ManufacturersDao dao = converterManufacturer.to(manufacturer);
        return converterManufacturer.from(manufacturesRepository.save(dao));
    }


    public List<ManufacturersDto> findByName(String query) {
        return manufacturesRepository.findByNameLikeIgnoreCase("%" + query + "%").stream()
                .map(converterManufacturer::from).collect(Collectors.toList());
    }

    @Override
    public Optional<ManufacturersDto> findById(UUID id) {
        return manufacturesRepository.findById(id).map(converterManufacturer::from);
    }

    @Override
    public Set<ManufacturersDto> findAll() {
        return manufacturesRepository.findAll().stream().map(converterManufacturer::from)
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(ManufacturersDto manufacturer) {
        manufacturesRepository.delete(converterManufacturer.to(manufacturer));
    }

}
