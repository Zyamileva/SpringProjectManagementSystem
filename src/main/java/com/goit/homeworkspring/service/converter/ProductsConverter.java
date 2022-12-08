package com.goit.homeworkspring.service.converter;

import com.goit.homeworkspring.model.dao.ProductsDao;
import com.goit.homeworkspring.model.dto.ProductsDto;
import org.springframework.stereotype.Service;

@Service

public class ProductsConverter implements Converter<ProductsDto, ProductsDao> {
    ManufacturesConverter manufacturesConverter;

    public ProductsConverter(ManufacturesConverter manufacturesConverter) {
        this.manufacturesConverter = manufacturesConverter;
    }

    public ProductsDto from(ProductsDao entity) {
        ProductsDto productsDto = new ProductsDto();
        productsDto.setId(entity.getId());
        productsDto.setName(entity.getName());
        productsDto.setPrice(entity.getPrice()/100);
        productsDto.setManufacturers(manufacturesConverter.from(entity.getManufactures()));
        return productsDto;
    }

    public ProductsDao to(ProductsDto entity) {
        ProductsDao productsDao = new ProductsDao();
        productsDao.setId(entity.getId());
        productsDao.setName(entity.getName());
        productsDao.setPrice(entity.getPrice()*100);
        productsDao.setManufactures(manufacturesConverter.to(entity.getManufacturers()));
        return productsDao;
    }
}