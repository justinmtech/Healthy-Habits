package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Habits;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class VisualizerController {

    @Autowired
    UserService userService;
    @Autowired
    SessionService sessionService;

    @RequestMapping("/visualizer")
    public String showGraph(Model model) {
        userService.getAllUsers();
        Optional<SiteUsers> siteUser = userService.getAllUsers().stream().findFirst();

        model.addAttribute("totalHabits", siteUser.get().getHabits().size());
        model.addAttribute("habits", siteUser.get().getHabits());
        model.addAttribute("siteUser", siteUser.get());

/*        Map<String, Integer> habitMap = new HashMap<>();
        for (int i = 0; i < habitList.size(); i++) {}*/

        return "visualizer";
    }
}
