package com.justin.healthyhabits.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        return "home";
    }
}
