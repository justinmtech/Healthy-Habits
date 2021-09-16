package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.CustomUserDetailsService;
import com.justin.healthyhabits.services.DataValidation;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Habit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveHabitController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUserDetailsService userd;

    @GetMapping("/habits-remove")
    public String getPage(Model model) {
        model.addAttribute("habit", new Habit());
        return "habitsremovepage";
    }

    @PostMapping("habits-remove")
    public String removeHabit(@ModelAttribute Habit habit, Model model) {
        model.addAttribute("habit", habit);
        if (DataValidation.isValid(habit.getName(), 3, 32)) {
            try {
                userd.getUser().getHabits().removeIf(h -> h.getName().equals(habit.getName()));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error!");
            }
        userService.saveUser(userd.getUser());
        }
        return "habitsremovepage";
    }
}
