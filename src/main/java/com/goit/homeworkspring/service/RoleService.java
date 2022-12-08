package com.goit.homeworkspring.service;

import com.goit.homeworkspring.model.dto.RoleDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleService {

    RoleDto saveRole(RoleDto roleDto);

    List<RoleDto> findByName(String name);

    Optional<RoleDto> findById(UUID id);

    Set<RoleDto> findAll();

    void delete(RoleDto role);

    RoleDto update(RoleDto role);
}
