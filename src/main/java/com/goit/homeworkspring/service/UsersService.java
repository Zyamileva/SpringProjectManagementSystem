package com.goit.homeworkspring.service;

import com.goit.homeworkspring.model.dto.UsersDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UsersService {

    UsersDto saveUser(UsersDto usersDto);

    List<UsersDto> findByLastname(String name);

    Optional<UsersDto> findById(UUID id);

    Set<UsersDto> findAll();

    void delete(UsersDto skills);

    UsersDto update(UsersDto skills);
}