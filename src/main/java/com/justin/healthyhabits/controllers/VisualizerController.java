package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
public class VisualizerController {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @RequestMapping("/visualizer")
    public String showGraph(Model model) {
        int userId;

        try {
            userId = sessionService.getAllSessions().stream().findAny().orElseThrow().getSiteUser().getUserId();
            model.addAttribute("totalHabits", userService.getUser(userId).orElseThrow().getHabits().size());
            model.addAttribute("habits", userService.getUser(userId).orElseThrow().getHabits());
            model.addAttribute("siteUser", userService.getUser(userId));
        } catch (NoSuchElementException | NullPointerException e) {
            System.out.println(e.toString());
            return "errorpage";
        }
        return "visualizer";
    }
}
