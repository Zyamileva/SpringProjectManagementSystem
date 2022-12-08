package com.goit.homeworkspring.controller.manufacturer;


import com.goit.homeworkspring.model.dao.ManufacturersDao;
import com.goit.homeworkspring.model.dto.ManufacturersDto;
import com.goit.homeworkspring.model.dto.ProductsDto;
import com.goit.homeworkspring.service.ManufacturersServiceImpl;
import com.goit.homeworkspring.service.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/manufacturer")
public class Manufacturer {
    private final ManufacturersServiceImpl manufacturers;
    private final Converter<ManufacturersDto, ManufacturersDao> converterManufacturer;

    @GetMapping("/all")
    protected ModelAndView findAll() {
        ModelAndView model = new ModelAndView("manufacturers/all");
        model.getModelMap().addAttribute("manufacturers", manufacturers.findAll());
        return model;
    }

    @GetMapping("/{manufacturer_id}")
    protected ModelAndView get(@PathVariable("manufacturer_id") UUID id) {
        ModelAndView model = new ModelAndView("manufacturers/products");
        model.getModelMap().addAttribute("products", manufacturers.findById(id).get().getProducts());
        return model;
    }

    @GetMapping("/create/form")
    protected ModelAndView createForm() {
        ModelAndView model = new ModelAndView("manufacturers/createForm");
        return model;
    }

    @PostMapping("/create")
    protected ModelAndView create(ManufacturersDto manufacturersDto) {
        ModelAndView model = new ModelAndView("message");
        if (!manufacturers.findByName(manufacturersDto.getName()).isEmpty()) {
            model.addObject("message", "Manufacturer " + manufacturersDto.getName() + " create yet");
            return model;
        }
        ManufacturersDto manufacturer = manufacturers.saveManufacturer(manufacturersDto);
        model.addObject("message", "Manufacturer " + manufacturer.getName() + " create");
        return model;
    }

    @GetMapping("/delete/form")
    protected ModelAndView deleteForm() {
        ModelAndView model = new ModelAndView("manufacturers/deleteForm");
        model.getModelMap().addAttribute("manufacturers", manufacturers.findAll());
        return model;
    }

    @PostMapping("/delete")
    protected ModelAndView delete(@RequestParam(value = "manufacturer") ManufacturersDao manufacturerDao) {
        ModelAndView model = new ModelAndView("message");
        manufacturers.delete(converterManufacturer.from(manufacturerDao));
        model.addObject("message", "Manufacturer " + manufacturerDao.getName() + " delete");
        return model;
    }

    @GetMapping("/find/form")
    protected ModelAndView findByNameForm() {
        ModelAndView model = new ModelAndView("manufacturers/findForm");
        return model;
    }

    @PostMapping("/find")
    protected ModelAndView findByName(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("manufacturers/find");
        if (!manufacturers.findByName(name).isEmpty()) {
            model.getModelMap().addAttribute("manufacturers", manufacturers.findByName(name));
            return model;
        }
        model.addObject("manufacturer", "Manufacturer " + name + " does not find");
        return model;
    }

    @GetMapping("/update/form")
    protected ModelAndView updateForm() {
        ModelAndView model = new ModelAndView("manufacturers/updateForm");
        model.getModelMap().addAttribute("manufacturers", manufacturers.findAll());
        return model;
    }

    @PostMapping("/update")
    protected ModelAndView update(@RequestParam(value = "manufacturer") ManufacturersDao manufacturerDao) {
        ModelAndView model = new ModelAndView("manufacturers/update");
        model.addObject("manufacturer", manufacturerDao);
        return model;
    }

    @PostMapping("/update/{manufacturer_id}")
    protected ModelAndView get(@PathVariable("manufacturer_id") UUID id,
                               ManufacturersDto manufacturersDto) {
        ManufacturersDto manufacturerUpdate = manufacturers.findById(id).get();
        manufacturerUpdate.setName(manufacturersDto.getName());
        manufacturers.update(manufacturerUpdate);
        ModelAndView model = new ModelAndView("message");
        model.addObject("message", "Manufacturer " + manufacturerUpdate.getName() + " update.");
        return model;
    }

}