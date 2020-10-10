package com.justin.healthyhabits.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginSuccessfulController {

    @RequestMapping("loginsuccessful")
    public String loginSuccessful() {
        return "loginsuccessful";
    }
}
