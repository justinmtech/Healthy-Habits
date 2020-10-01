package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Habits;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HabitsController {

    @Autowired
    UserService userService;

    @GetMapping("/habits")
    public String habitForm(Model model) {
        model.addAttribute("habit", new Habits());
        return "habitspage";
    }

    @PostMapping("/habits")
    public String habitSubmit(@ModelAttribute Habits habit, Model model) {
        model.addAttribute("habit", habit);
        List<Habits> habitList = new ArrayList<>();
        habitList.add(habit);
        userService.saveUser("Justin", (new SiteUsers("Justin", "password1", habitList)));
        return "habitspage";
    }
}
