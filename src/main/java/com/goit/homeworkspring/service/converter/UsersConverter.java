package com.goit.homeworkspring.service.converter;

import com.goit.homeworkspring.model.dao.UsersDao;
import com.goit.homeworkspring.model.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service

public class UsersConverter implements Converter<UsersDto, UsersDao> {
    RoleConverter roleConverter;

    public UsersConverter(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Override
    public UsersDto from(UsersDao entity) {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(entity.getId());
        usersDto.setFirstname(entity.getFirstname());
        usersDto.setLastname(entity.getLastname());
        usersDto.setEmail(entity.getEmail());
        usersDto.setPassword(entity.getPassword());
        if (entity.getRoles() != null) {
        usersDto.setRoles(entity.getRoles().stream().map(el -> roleConverter.from(el))
                .collect(Collectors.toSet()));}
        return usersDto;
    }

    @Override
    public UsersDao to(UsersDto entity) {
        UsersDao usersDao = new UsersDao();
        usersDao.setId(entity.getId());
        usersDao.setFirstname(entity.getFirstname());
        usersDao.setLastname(entity.getLastname());
        usersDao.setEmail(entity.getEmail());
        usersDao.setPassword(entity.getPassword());
        if (entity.getRoles() != null) {
        usersDao.setRoles(entity.getRoles().stream().map(el -> roleConverter.to(el))
                .collect(Collectors.toSet()));}
        return usersDao;
    }
}