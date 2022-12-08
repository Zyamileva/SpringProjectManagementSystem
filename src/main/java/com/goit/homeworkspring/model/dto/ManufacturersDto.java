package com.goit.homeworkspring.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Component
@Data
public class ManufacturersDto {
    private UUID id;
    private String name;
    private Set<ProductsDto> products = new HashSet<>();

    public ManufacturersDto(UUID id, String name, Set<ProductsDto> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public ManufacturersDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturersDto that = (ManufacturersDto) o;
        return id == that.id && name.equals(that.name) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }
}