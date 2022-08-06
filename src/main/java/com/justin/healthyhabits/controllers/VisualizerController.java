package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisualizerController {

    @Autowired
    CustomUserDetailsService userd;

    @GetMapping("/visualizer")
    public String graph(Model model) {
        model.addAttribute("userHabits", userd.getUser().getHabits());
        model.addAttribute("user", userd.getUser());
        return "visualizer";
    }
}
