package com.goit.homeworkspring.controller;

import com.goit.homeworkspring.model.dto.RoleDto;
import com.goit.homeworkspring.model.dto.UsersDto;
import com.goit.homeworkspring.service.RoleServiceImpl;
import com.goit.homeworkspring.service.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final UsersServiceImpl users;
    private final RoleServiceImpl role;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UsersDto usersDto, Map<String, Object> model) {
        if (!users.findByEmail(usersDto.getEmail()).isEmpty()) {
            model.put("message", "User " + usersDto.getLastname()
                    + usersDto.getFirstname() + " with this email already exist.");
            return "registration";
        }
        RoleDto roleUser = role.findByName("User").get(0);
        usersDto.getRoles().add(roleUser);
        UsersDto user = users.saveUser(usersDto);
        return "redirect:/login";
    }
}