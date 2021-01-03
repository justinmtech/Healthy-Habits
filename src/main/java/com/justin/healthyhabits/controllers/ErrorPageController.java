package com.justin.healthyhabits.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("SameReturnValue")
@Controller
public class ErrorPageController {

    @RequestMapping("/errorpage")
    public String errorPage() {
        return "errorpage";
    }

}
