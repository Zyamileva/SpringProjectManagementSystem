package com.goit.homeworkspring.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class ProductsDto {
    private UUID id;
    private String name;

    private double price;

    private ManufacturersDto manufacturers;

    public ProductsDto(UUID id, String name, double price, ManufacturersDto manufacturers) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturers = manufacturers;
    }

    public ProductsDto() {
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}