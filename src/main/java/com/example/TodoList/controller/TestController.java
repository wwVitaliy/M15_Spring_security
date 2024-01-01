package com.example.TodoList.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @PostConstruct
    private void postConstruct() {
        System.out.println("--->>> TestController created.");
    }

    @GetMapping(value = "/test")
    public ModelAndView testMethod() {
        return new ModelAndView("test");
    }

}
