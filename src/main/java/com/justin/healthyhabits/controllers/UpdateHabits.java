package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Habit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UpdateHabits {

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @GetMapping("/updatehabits")
    public String showHabits(Model model) {
        List<Habit> habits = new ArrayList<>();
        try {
            habits = sessionService.getAllSessions().stream().findFirst().orElseThrow(NoSuchElementException::new).getSiteUser().getHabits();
        } catch (NoSuchElementException e) {
            System.out.println(e.getCause().toString());
        }
        model.addAttribute("habits", habits);

        return "updatehabits";
    }

    @PostMapping("/updatehabits")
    public String updateHabits(@ModelAttribute Habit habitRatings, Model model) {
        model.addAttribute("habitRatings", habitRatings);

        //habit.setRatings();

        return "updatehabits";
    }

}
