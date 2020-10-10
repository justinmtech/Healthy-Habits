package com.justin.healthyhabits.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterSuccessfulController {

    @RequestMapping("registersuccessful")
    public String registerSuccessful() {
        return "registersuccessful";
    }
}
