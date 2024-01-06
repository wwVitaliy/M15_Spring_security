package com.example.TodoList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping
public class LoggingController {


    @RequestMapping("/")
    public RedirectView defaultHome(){
        return new RedirectView("/note/list");
    }

}
