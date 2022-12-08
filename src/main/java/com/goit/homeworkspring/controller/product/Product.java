package com.goit.homeworkspring.controller.product;


import com.goit.homeworkspring.model.dao.ManufacturersDao;
import com.goit.homeworkspring.model.dao.ProductsDao;
import com.goit.homeworkspring.model.dto.ManufacturersDto;
import com.goit.homeworkspring.model.dto.ProductsDto;
import com.goit.homeworkspring.service.ManufacturersServiceImpl;
import com.goit.homeworkspring.service.ProductsServiceImpl;
import com.goit.homeworkspring.service.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class Product {
    private final ProductsServiceImpl products;
    private final Converter<ProductsDto, ProductsDao> converterProduct;
    private final ManufacturersServiceImpl manufacturers;

    @GetMapping("/all")
    protected ModelAndView findAll() {
        ModelAndView model = new ModelAndView("products/all");
        model.getModelMap().addAttribute("products", products.findAll());
        return model;
    }

    @GetMapping("/{product_id}")
    protected ModelAndView get(@PathVariable("product_id") UUID id) {
        ModelAndView model = new ModelAndView("products/manufacturer");
        ManufacturersDto manufacturers = products.findById(id).get().getManufacturers();
        model.addObject("manufacturer", manufacturers);
        return model;
    }

    @GetMapping("/create/form")
    protected ModelAndView createForm() {
        ModelAndView model = new ModelAndView("products/createForm");
        model.getModelMap().addAttribute("manufacturers", manufacturers.findAll());
        return model;
    }

    @PostMapping("/create")
    protected ModelAndView create(ProductsDto productsDto, @RequestParam(value = "manufacturer") UUID id) {//, ModelAndView modelAndView) {"manufacturer.id
        ModelAndView model = new ModelAndView("message");
        if (!products.findByName(productsDto.getName()).isEmpty()) {
            model.addObject("message", "Product " + productsDto.getName() + " create yet");
            return model;
        }
        productsDto.setManufacturers(manufacturers.findById(id).get());
        products.saveProduct(productsDto);
        model.addObject("message", "Product " + productsDto.getName() + " create");
        return model;
    }

    @GetMapping("/delete/form")
    protected ModelAndView deleteForm() {
        ModelAndView model = new ModelAndView("products/deleteForm");
        model.getModelMap().addAttribute("products", products.findAll());
        return model;
    }

    @PostMapping("/delete")
    protected ModelAndView delete(@RequestParam(value = "product") ProductsDao productsDao) {
        ModelAndView model = new ModelAndView("message");
        products.delete(converterProduct.from(productsDao));
        model.addObject("message", "Product " + productsDao.getName() + " delete");
        return model;
    }

    @GetMapping("/find/form")
    protected ModelAndView findByNameForm() {
        ModelAndView model = new ModelAndView("products/findForm");
        return model;
    }

    @PostMapping("/find")
    protected ModelAndView findByName(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("products/find");
        if (!products.findByName(name).isEmpty()) {
            model.getModelMap().addAttribute("products", products.findByName(name));
            return model;
        }
        model.addObject("product", "Product " + name + " does not find");
        return model;
    }

    @GetMapping("/update/form")
    protected ModelAndView updateForm() {
        ModelAndView model = new ModelAndView("products/updateForm");
        model.getModelMap().addAttribute("products", products.findAll());
        return model;
    }

    @PostMapping("/update")
    protected ModelAndView update(@RequestParam(value = "product") ProductsDao productsDao) {
        ModelAndView model = new ModelAndView("products/update");
        model.addObject("product", productsDao);
        return model;
    }

    @PostMapping("/update/{product_id}")
    protected ModelAndView get(@PathVariable("product_id") UUID id,
                               ProductsDto productsDto) {
        ProductsDto productUpdate = products.findById(id).get();
        productUpdate.setName(productsDto.getName());
        productUpdate.setPrice(productsDto.getPrice());
        products.update(productUpdate);
        ModelAndView model = new ModelAndView("message");
        model.addObject("message", "Product " + productUpdate.getName() + " update.");
        return model;
    }
}