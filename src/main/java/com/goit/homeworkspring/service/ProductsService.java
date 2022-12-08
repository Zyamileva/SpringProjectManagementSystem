package com.goit.homeworkspring.service;

import com.goit.homeworkspring.model.dto.ManufacturersDto;
import com.goit.homeworkspring.model.dto.ProductsDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProductsService {

    ProductsDto saveProduct(ProductsDto product);

    List<ProductsDto> findByName(String name);

    Optional<ProductsDto> findById(UUID id);

    Set<ProductsDto> findAll();

    void delete(ProductsDto product);

    ProductsDto update(ProductsDto product);
}
