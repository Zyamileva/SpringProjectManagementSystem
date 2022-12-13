package com.goit.homeworkspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("index.html")
public class IndexServlet {

    @GetMapping
    protected ModelAndView doGet() {
        ModelAndView result = new ModelAndView("index");
        return result;
    }
}
