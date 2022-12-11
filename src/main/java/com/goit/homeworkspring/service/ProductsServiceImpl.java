package com.goit.homeworkspring.service;

import com.goit.homeworkspring.model.dao.ProductsDao;
import com.goit.homeworkspring.model.dto.ProductsDto;
import com.goit.homeworkspring.repository.ProductsRepository;
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
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    private final Converter<ProductsDto, ProductsDao> converterProduct;

    @Override
    public ProductsDto saveProduct(ProductsDto product) {
        ProductsDao dao = converterProduct.to(product);
        return converterProduct.from(productsRepository.save(dao));
    }

    @Override
    public ProductsDto update(ProductsDto product) {
        ProductsDao dao = converterProduct.to(product);
        return converterProduct.from(productsRepository.save(dao));
    }

    @Override
    public List<ProductsDto> findByName(String query) {
        return productsRepository.findByNameLikeIgnoreCase("%" + query + "%").stream()
                .map(converterProduct::from).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductsDto> findById(UUID id) {
        return productsRepository.findById(id).map(converterProduct::from);
    }

    @Override
    public Set<ProductsDto> findAll() {
        return productsRepository.findAll().stream().map(converterProduct::from)
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(ProductsDto product) {
        productsRepository.delete(converterProduct.to(product));
    }

}