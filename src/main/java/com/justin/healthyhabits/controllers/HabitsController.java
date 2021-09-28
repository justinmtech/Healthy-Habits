package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.CustomUserDetailsService;
import com.justin.healthyhabits.services.DataValidation;
import com.justin.healthyhabits.services.LoggerService;
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
public class HabitsController {

    @Autowired
    LoggerService logger;

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
        model.addAttribute("habit", habit);
        model.addAttribute("user", userd.getUser());
        try {
            if (habit.getHabitType().equals("add")) {
                if (!DataValidation.isHabitValid((habit))) return "errorpage";
                else {
                    if (habitExists(habit)) {
                        updateHabit(habit);
                    } else {
                        addNewHabit(habit);
                    }
                }
            } else if (habit.getHabitType().equals("remove")) {
                    userd.getUser().getHabits().removeIf(h -> h.getName().equals(habit.getName()));
                }
            userService.saveUser(userd.getUser());

        } catch (NullPointerException | NoSuchElementException e) {
            logger.addToLog("HabitsController Catch Error: " + e.toString(), true);
            return "errorpage";
        }
        return "habitspage";
    }

    private List<Habit> getUserHabits() {
        return userd.getUser().getHabits();
    }

    private void addNewHabit(Habit habit) {
        ArrayList<ArrayList> data = new ArrayList<>();
        ArrayList<Long> dates = new ArrayList();
        dates.add(getDate());
        habit.setDates(dates);
        userd.getUser().getHabits().add(habit);
        data.add(dates);
        logger.addToLog("Habit " + habit.getName() + " added for user " + userd.getUser().getUsername(), false);
    }

    private void removeHabit(Habit habit) {
        userd.getUser().getHabits().remove(habit);
    }

    private boolean habitExists(Habit habit) {
        for (int i = 0; i < getUserHabits().size(); i++) {
            if (getUserHabits().get(i).getName().equals(habit.getName())) {
                return true;
            }
        }
        return false;
    }

    private void updateHabit(Habit habit) {
        for (int i = 0; i < getUserHabits().size(); i++) {
            if (getUserHabits().get(i).getName().equals(habit.getName())) {
                userd.getUser().getHabits().get(i).getDates().add(getDate());
                userd.getUser().getHabits().get(i).addRating(habit.getRatings().get(0));
                userService.saveUser(userd.getUser());
            }
        }
        logger.addToLog("Habit " + habit.getName() + " updated for user " + userd.getUser().getUsername(), false);
    }

    private long getDate() {
        return System.currentTimeMillis();
    }
}