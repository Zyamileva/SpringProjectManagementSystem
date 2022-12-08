package com.goit.homeworkspring.service.converter;

public interface Converter <E, T>{

    E from(T entity);
    T to (E entity);
}