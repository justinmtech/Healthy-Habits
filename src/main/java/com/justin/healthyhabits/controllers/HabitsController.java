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

import java.util.ArrayList;
import java.util.Map;
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
        try {
            if (habit.getHabitType().equals("add")) {
                if (!DataValidation.isHabitValid((habit))) return "errorpage";
                else {
                    if (habitExists(habit)) updateHabit(habit);
                    else addNewHabit(habit);
                    }
            } else if (habit.getHabitType().equals("remove")) {
                userd.getUser().getHabits().remove(habit.getName());
            }
            userService.saveUser(userd.getUser());

        } catch (NullPointerException | NoSuchElementException e) {
            return "errorpage";
        }
        return "habitspage";
    }

    private Map<String, Habit> getUserHabits() {
        return userd.getUser().getHabits();
    }

    @SuppressWarnings("unchecked")
    private void addNewHabit(Habit habit) {
        ArrayList<Long> dates = new ArrayList<>();
        dates.add(getDate());
        habit.setDates(dates);
        userd.getUser().getHabits().put(habit.getName(), habit);
    }

    private boolean habitExists(Habit habit) {
        return getUserHabits().containsKey(habit.getName());
    }

    private void updateHabit(Habit habit) {
        if (getUserHabits().get(habit.getName()) != null) {
            userd.getUser().getHabits().get(habit.getName()).addDate(getDate());
            userd.getUser().getHabits().get(habit.getName()).addRating(habit.getRatings().get(0));
            userService.saveUser(userd.getUser());
        }
    }

    private long getDate() {
        return System.currentTimeMillis();
    }
}