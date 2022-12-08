package com.goit.homeworkspring.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class UsersDto {
    private UUID id;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private Set<RoleDto> roles = new HashSet<>();

    public UsersDto() {
    }

    public UsersDto(UUID id, String password, String email, String firstname, String lastname, Set<RoleDto> roles) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersDto)) return false;
        UsersDto usersDto = (UsersDto) o;
        return Objects.equals(id, usersDto.id) && password.equals(usersDto.password) && email.equals(usersDto.email) && firstname.equals(usersDto.firstname) && lastname.equals(usersDto.lastname) && Objects.equals(roles, usersDto.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, email, firstname, lastname, roles);
    }
}