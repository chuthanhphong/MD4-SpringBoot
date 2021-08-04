package com.codegym.thuchanh1springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public ModelAndView show(){
        return new ModelAndView("/greeting");
    }
}
