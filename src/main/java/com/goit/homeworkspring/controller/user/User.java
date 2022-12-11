package com.goit.homeworkspring.controller.user;


import com.goit.homeworkspring.model.dao.ManufacturersDao;
import com.goit.homeworkspring.model.dao.UsersDao;
import com.goit.homeworkspring.model.dto.ManufacturersDto;
import com.goit.homeworkspring.model.dto.ProductsDto;
import com.goit.homeworkspring.model.dto.RoleDto;
import com.goit.homeworkspring.model.dto.UsersDto;
import com.goit.homeworkspring.service.ManufacturersServiceImpl;
import com.goit.homeworkspring.service.RoleServiceImpl;
import com.goit.homeworkspring.service.UsersServiceImpl;
import com.goit.homeworkspring.service.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class User {
    private final UsersServiceImpl users;
    private final RoleServiceImpl role;
    private final Converter<UsersDto, UsersDao> converterUser;
    @Secured(value = {"ROLE_Admin", "ROLE_User"})
    @GetMapping("/all")
    protected ModelAndView findAll() {
        ModelAndView model = new ModelAndView("users/all");
        Set<UsersDto> all = users.findAll();
        model.getModelMap().addAttribute("users", users.findAll());
        return model;
    }
    @Secured(value = {"ROLE_Admin", "ROLE_User"})
    @GetMapping("/{user_id}")
    protected ModelAndView get(@PathVariable("user_id") UUID id) {
        ModelAndView model = new ModelAndView("users/role");
        model.getModelMap().addAttribute("roles", users.findById(id).get().getRoles());
        return model;
    }
    @Secured(value = {"ROLE_Admin"})
    @GetMapping("/create/form")
    protected ModelAndView createForm() {
        ModelAndView model = new ModelAndView("users/createForm");
        return model;
    }
    @Secured(value = {"ROLE_Admin"})
    @PostMapping("/create")
    protected ModelAndView create(UsersDto usersDto) {
        if (!users.findByEmail(usersDto.getEmail()).isEmpty()) {
            ModelAndView model = new ModelAndView("message");
            model.addObject("message", "User " + usersDto.getLastname()
                    + usersDto.getFirstname() + " with this email already exist.");
            return model;
        }
        ModelAndView model = new ModelAndView("users/createUserRoleForm");
        UsersDto user = users.saveUser(usersDto);
        model.addObject("usersDto", user);
        model.getModelMap().addAttribute("roles", role.findAll());
        return model;
    }
    @Secured(value = {"ROLE_Admin"})
    @PostMapping("/create/{user_id}")
    protected ModelAndView createRole(@PathVariable("user_id") UUID id, @RequestParam(value = "rolesUser") UUID[] uuid) {  //, RoleDto roleDto){//@RequestParam (value = "userRole") List<Object> kk) {
        ModelAndView model = new ModelAndView("message");
        Set<RoleDto> roles = Arrays.stream(uuid).map(role::findById).filter(Optional::isPresent).
                map(Optional::get).collect(Collectors.toSet());
        UsersDto usersDto = users.findById(id).get();
        if (users.findById(id).isPresent()) {
            usersDto.setRoles(roles);
            users.saveUser(usersDto);
        }
        model.addObject("message", "User save.");
        return model;
    }
    @Secured(value = {"ROLE_Admin"})
    @GetMapping("/delete/form")
    protected ModelAndView deleteForm() {
        ModelAndView model = new ModelAndView("users/deleteForm");
        model.getModelMap().addAttribute("users", users.findAll());
        return model;
    }
    @Secured(value = {"ROLE_Admin"})
    @PostMapping("/delete")
    protected ModelAndView delete(@RequestParam(value = "user") UsersDao usersDao) {
        ModelAndView model = new ModelAndView("message");
        users.delete(converterUser.from(usersDao));
        model.addObject("message", "User " + usersDao.getLastname() + " " +
                usersDao.getFirstname() + " delete");
        return model;
    }
    @Secured(value = {"ROLE_Admin", "ROLE_User"})
    @GetMapping("/find/form")
    protected ModelAndView findByNameForm() {
        ModelAndView model = new ModelAndView("users/findForm");
        return model;
    }
    @Secured(value = {"ROLE_Admin", "ROLE_User"})
    @PostMapping("/find")
    protected ModelAndView findByName(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("users/find");
        if (!users.findByLastname(name).isEmpty()) {
            model.getModelMap().addAttribute("users", users.findByLastname(name));
            return model;
        }
        model.addObject("user", "user " + name + " does not find");
        return model;
    }
    @Secured(value = {"ROLE_Admin"})
    @GetMapping("/update/form")
    protected ModelAndView updateForm() {
        ModelAndView model = new ModelAndView("users/updateForm");
        model.getModelMap().addAttribute("users", users.findAll());
        return model;
    }
    @Secured(value = {"ROLE_Admin"})
    @PostMapping("/update")
    protected ModelAndView update(@RequestParam(value = "user") UsersDao usersDao) {
        ModelAndView model = new ModelAndView("users/update");
        model.addObject("user", usersDao);
        return model;
    }
    @Secured(value = {"ROLE_Admin"})
    @PostMapping("/update/{user_id}")
    protected ModelAndView get(@PathVariable("user_id") UUID uuid,
                               UsersDto usersDto) {
        UsersDto userUpdate = users.findById(uuid).get();
        userUpdate.setEmail(usersDto.getEmail());
        userUpdate.setPassword(usersDto.getPassword());
        userUpdate.setLastname(usersDto.getLastname());
        userUpdate.setFirstname(usersDto.getFirstname());
        users.update(userUpdate);
        ModelAndView model = new ModelAndView("message");
        model.addObject("message", "Manufacturer " + userUpdate.getLastname() +
                "  " + userUpdate.getFirstname() + " update.");
        return model;
    }
}