package com.goit.homeworkspring.service.converter;

import com.goit.homeworkspring.model.dao.ManufacturersDao;
import com.goit.homeworkspring.model.dao.ProductsDao;
import com.goit.homeworkspring.model.dto.ManufacturersDto;
import com.goit.homeworkspring.model.dto.ProductsDto;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service

public class ManufacturesConverter implements Converter<ManufacturersDto, ManufacturersDao> {

    @Override
    public ManufacturersDto from(ManufacturersDao entity) {
        ManufacturersDto manufacturersDto = new ManufacturersDto();
        manufacturersDto.setId(entity.getId());
        manufacturersDto.setName(entity.getName());
        if (entity.getProducts() != null) {
            manufacturersDto.setProducts(entity.getProducts().stream().filter(Objects::nonNull)
                    .map(this::productsFrom).collect(Collectors.toSet()));
        }
        return manufacturersDto;
    }

    @Override
    public ManufacturersDao to(ManufacturersDto entity) {
        ManufacturersDao manufacturersDao = new ManufacturersDao();

        manufacturersDao.setId(entity.getId());
        manufacturersDao.setName(entity.getName());
        if (entity.getProducts() != null) {
            manufacturersDao.setProducts(entity.getProducts().stream().filter(Objects::nonNull)
                    .map(this::productsTo).collect(Collectors.toSet()));
        }
        return manufacturersDao;
    }

    public ProductsDto productsFrom(ProductsDao entity) {
        ProductsDto productsDto = new ProductsDto();
        productsDto.setId(entity.getId());
        productsDto.setName(entity.getName());
        productsDto.setPrice(entity.getPrice()/100);
        return productsDto;
    }

    public ProductsDao productsTo(ProductsDto entity) {
        ProductsDao productsDao = new ProductsDao();
        productsDao.setId(entity.getId());
        productsDao.setName(entity.getName());
        productsDao.setPrice(entity.getPrice()*100);
        return productsDao;
    }
}