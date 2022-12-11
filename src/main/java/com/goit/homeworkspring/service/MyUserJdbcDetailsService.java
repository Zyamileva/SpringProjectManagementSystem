package com.goit.homeworkspring.service;

import com.goit.homeworkspring.model.dto.RoleDto;
import com.goit.homeworkspring.model.dto.UsersDto;
import com.goit.homeworkspring.service.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MyUserJdbcDetailsService implements UserDetailsService {
    private final UsersServiceImpl users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersDto user = users.findByEmail(username).get(0);

        Set<GrantedAuthority> roles = user.getRoles().stream().map(RoleDto::getName).map(it -> it.replace(it, "ROLE_" + it))
                .map((it -> (GrantedAuthority) () -> it)).collect(Collectors.toSet());

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return roles;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}