package com.goit.homeworkspring.controller.role;


import com.goit.homeworkspring.model.dao.ManufacturersDao;
import com.goit.homeworkspring.model.dao.RoleDao;
import com.goit.homeworkspring.model.dto.ManufacturersDto;
import com.goit.homeworkspring.model.dto.RoleDto;
import com.goit.homeworkspring.model.dto.UsersDto;
import com.goit.homeworkspring.service.RoleServiceImpl;
import com.goit.homeworkspring.service.UsersServiceImpl;
import com.goit.homeworkspring.service.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/role")
public class Role {
    private final RoleServiceImpl roles;
    private final Converter<RoleDto, RoleDao> converterRole;
    private final UsersServiceImpl users;

    @GetMapping("/all")
    protected ModelAndView findAll() {
        ModelAndView model = new ModelAndView("roles/all");
        model.getModelMap().addAttribute("roles", roles.findAll());
        return model;
    }

    @GetMapping("/{role_id}")
    protected ModelAndView get(@PathVariable("role_id") UUID id) {
        ModelAndView model = new ModelAndView("roles/users");
        if (roles.findById(id).isPresent()) {
            Set<UsersDto> users = roles.findById(id).get().getUsers();
            model.getModelMap().addAttribute("users", users);
        }
        return model;
    }

    @GetMapping("/create/form")
    protected ModelAndView createForm() {
        ModelAndView model = new ModelAndView("roles/createForm");
        model.getModelMap().addAttribute("roles", roles.findAll());
        return model;
    }

    @PostMapping("/create")
    protected ModelAndView create(RoleDto roleDto) {
        ModelAndView model = new ModelAndView("message");
        if (!roles.findByName(roleDto.getName()).isEmpty()) {
            model.addObject("message", "Role " + roleDto.getName() + " create yet");
            return model;
        }
        RoleDto role = roles.saveRole(roleDto);
        model.addObject("message", "Role " + role.getName() + " create");
        return model;
    }

    @GetMapping("/delete/form")
    protected ModelAndView deleteForm() {
        ModelAndView model = new ModelAndView("roles/deleteForm");
        model.getModelMap().addAttribute("roles", roles.findAll());
        return model;
    }

    @PostMapping("/delete")
    protected ModelAndView delete(@RequestParam(value = "role") RoleDao roleDao) {
        ModelAndView model = new ModelAndView("message");
        roles.delete(converterRole.from(roleDao));
        model.addObject("message", "Role " + roleDao.getName() + " delete");
        return model;
    }

    @GetMapping("/find/form")
    protected ModelAndView findByNameForm() {
        ModelAndView model = new ModelAndView("roles/findForm");
        return model;
    }

    @PostMapping("/find")
    protected ModelAndView findByName(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("roles/find");
        if (!roles.findByName(name).isEmpty()) {
            model.getModelMap().addAttribute("roles", roles.findByName(name));
            return model;
        }
        model.addObject("role", "Role " + name + " does not find");
        return model;
    }

    @GetMapping("/update/form")
    protected ModelAndView updateForm() {
        ModelAndView model = new ModelAndView("roles/updateForm");
        model.getModelMap().addAttribute("roles", roles.findAll());
        return model;
    }

    @PostMapping("/update")
    protected ModelAndView update(@RequestParam(value = "role") RoleDao roleDao) {
        ModelAndView model = new ModelAndView("roles/update");
        model.addObject("role", roleDao);
        return model;
    }

    @PostMapping("/update/{role_id}")
    protected ModelAndView get(@PathVariable("role_id") UUID uuid, RoleDto roleDto) {
        RoleDto roleUpdate = roles.findById(uuid).get();
        roleUpdate.setName(roleDto.getName());
        roles.update(roleUpdate);
        ModelAndView model = new ModelAndView("message");
        model.addObject("message", "Role " + roleUpdate.getName() + " update.");
        return model;
    }

}