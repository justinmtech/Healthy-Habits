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

import java.util.NoSuchElementException;

@Controller
public class HabitsController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUserDetailsService userd;

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/habits")
    public String habitForm(Model model) {
        model.addAttribute("habit", new Habit());
        model.addAttribute("user", userd.getUser());
        return "habitspage";
    }

    @PostMapping("/habits")
    public String habitSubmit(@ModelAttribute Habit habit, Model model) {
        habit.setName(habit.getName().trim());

        model.addAttribute("habit", habit);
        model.addAttribute("user", userd.getUser());

        String habitSubmissionType = habit.getHabitType();
        boolean habitExists = userd.getUser().hasHabit(habit.getName());

        try {
            if (!DataValidation.isHabitValid((habit))) return "errorpage";
            if (habitSubmissionType.equals("add")) {
                if (habitExists) userd.getUser().saveHabit(habit);
                else userd.getUser().addHabit(habit);
            } else if (habitSubmissionType.equals("remove")) {
                userd.getUser().removeHabit(habit.getName());
            }
            userService.saveUser(userd.getUser());
        } catch (NullPointerException | NoSuchElementException e) {
            return "errorpage";
        }
        return "habitspage";
    }
}