package com.goit.homeworkspring.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Component
@Data
public class RoleDto {
    private UUID id;

    private String name;

    private Set<UsersDto> users = new HashSet<>();

    public RoleDto() {
    }

    public RoleDto(UUID id, String name, Set<UsersDto> users) {

        this.id = id;
        this.name = name;
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return id == roleDto.id && name.equals(roleDto.name) && Objects.equals(users, roleDto.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, users);
    }
}