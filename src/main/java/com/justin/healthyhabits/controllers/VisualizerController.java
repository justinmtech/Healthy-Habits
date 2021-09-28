package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.CustomUserDetailsService;
import com.justin.healthyhabits.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;

@Controller
public class VisualizerController {

    @Autowired
    LoggerService logger;

    @Autowired
    CustomUserDetailsService userd;

    @GetMapping("/visualizer")
    public String graph(Model model) throws ParseException {
        model.addAttribute("userHabits", userd.getUser().getHabits());
        model.addAttribute("user", userd.getUser());
        return "visualizer";
    }
}
