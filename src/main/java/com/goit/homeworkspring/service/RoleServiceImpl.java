package com.goit.homeworkspring.service;

import com.goit.homeworkspring.model.dao.RoleDao;
import com.goit.homeworkspring.model.dao.UsersDao;
import com.goit.homeworkspring.model.dto.RoleDto;
import com.goit.homeworkspring.repository.RoleRepository;
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

public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final Converter<RoleDto, RoleDao> converterRole;

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        return converterRole.from(roleRepository.save(converterRole.to(roleDto)));
    }

    @Override
    public List<RoleDto> findByName(String query) {
        return roleRepository.findByNameLikeIgnoreCase("%" + query + "%").stream()
                .map(converterRole::from).collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDto> findById(UUID id) {
        return roleRepository.findById(id).map(converterRole::from);
    }

    @Override
    public Set<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(converterRole::from)
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(RoleDto role) {
        roleRepository.delete(converterRole.to(role));
    }

    @Override
    public RoleDto update(RoleDto role) {
        RoleDao dao = converterRole.to(role);
        return converterRole.from(roleRepository.save(dao));
    }
}