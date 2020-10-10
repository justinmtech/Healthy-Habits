package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController {
    private final Logger logger = new Logger();

    @RequestMapping("/errorpage")
    public String errorPage() {
        return "errorpage";
    }

}
