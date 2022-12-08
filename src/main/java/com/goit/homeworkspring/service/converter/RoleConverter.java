package com.goit.homeworkspring.service.converter;

import com.goit.homeworkspring.model.dao.RoleDao;
import com.goit.homeworkspring.model.dao.UsersDao;
import com.goit.homeworkspring.model.dto.RoleDto;
import com.goit.homeworkspring.model.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoleConverter implements Converter<RoleDto, RoleDao> {

    @Override
    public RoleDto from(RoleDao entity) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());
        if (entity.getUsers() != null) {
            roleDto.setUsers(entity.getUsers().stream().map(this::userFrom)
                    .collect(Collectors.toSet()));
        }
        return roleDto;
    }

    @Override
    public RoleDao to(RoleDto entity) {
        RoleDao roleDao = new RoleDao();
        roleDao.setId(entity.getId());
        roleDao.setName(entity.getName());
        if (entity.getUsers() != null) {
            roleDao.setUsers(entity.getUsers().stream().map(this::userTo)
                    .collect(Collectors.toSet()));
        }
        return roleDao;
    }

    public UsersDto userFrom(UsersDao entity) {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(entity.getId());
        usersDto.setFirstname(entity.getFirstname());
        usersDto.setLastname(entity.getLastname());
        usersDto.setEmail(entity.getEmail());
        usersDto.setPassword(entity.getPassword());
        return usersDto;
    }

    public UsersDao userTo(UsersDto entity) {
        UsersDao usersDao = new UsersDao();
        usersDao.setId(entity.getId());
        usersDao.setFirstname(entity.getFirstname());
        usersDao.setLastname(entity.getLastname());
        usersDao.setEmail(entity.getEmail());
        usersDao.setPassword(entity.getPassword());
        return usersDao;
    }
}
