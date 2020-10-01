package com.justin.healthyhabits.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HabitsController {

    @RequestMapping("habits")
    public String habits(Model model) {
        return "habitspage";
    }
}
