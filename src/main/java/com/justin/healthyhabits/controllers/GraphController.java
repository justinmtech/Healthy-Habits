package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.LoggerService;
import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class GraphController {

    @Autowired
    LoggerService loggerService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @GetMapping("/graph")
    public String graph(Model model) {
        Map<String, Integer> habits = new LinkedHashMap<>();

        User user = sessionService.getAllSessions().stream().findFirst().orElse(null).getSiteUser();

        for (int i = 0; i < user.getHabits().size(); i++) {
        habits.put(user.getHabits().get(i).getName(), user.getHabits().get(i).getRating());
        }
        model.addAttribute("habits", habits);

        return "graph";
    }

}
