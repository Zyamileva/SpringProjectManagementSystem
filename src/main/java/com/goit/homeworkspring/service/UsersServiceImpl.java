package com.goit.homeworkspring.service;

import com.goit.homeworkspring.model.dao.UsersDao;
import com.goit.homeworkspring.model.dto.UsersDto;
import com.goit.homeworkspring.repository.UsersRepository;
import com.goit.homeworkspring.service.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final Converter<UsersDto, UsersDao> converterUser;

    @Override
    public UsersDto saveUser(UsersDto user) {
        UsersDao dao = converterUser.to(user);
        return converterUser.from(usersRepository.save(dao));
    }

    @Override
    public List<UsersDto> findByLastname(String query) {
        return usersRepository.findByLastnameLikeIgnoreCase("%" + query + "%").stream()
                .map(converterUser::from).collect(Collectors.toList());
    }

    public List<UsersDto> findByEmail(String query) {
        return usersRepository.findByEmailLikeIgnoreCase(query).stream()
                .map(converterUser::from).collect(Collectors.toList());
    }

    @Override
    public Optional<UsersDto> findById(UUID id) {
        return usersRepository.findById(id).map(converterUser::from);
    }

    @Override
    public Set<UsersDto> findAll() {
        return usersRepository.findAll().stream().map(converterUser::from)
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(UsersDto user) {
        usersRepository.delete(converterUser.to(user));
    }

    @Override
    public UsersDto update(UsersDto user) {
        UsersDao dao = converterUser.to(user);
        return converterUser.from(usersRepository.save(dao));
    }
}
