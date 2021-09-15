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
        return "habitspage";
    }

    @PostMapping("/habits")
    public String habitSubmit(@ModelAttribute Habit habit, Model model) {
        model.addAttribute("habit", habit);
        try {
            if (isDataInvalid(habit)) return "errorpage";
            else {
                if (checkForExistingHabit(habit)) {
                    updateExistingHabit(habit);
                    userService.saveUser(userd.getUser());
                }
                else AddHabitToUser(habit);
                userService.saveUser(userd.getUser());
            }
        } catch (NullPointerException | NoSuchElementException e) {
            System.out.println("HabitController Habit Submit Error: " + e.toString());
            System.out.println("HabitController Habit Submit Error: " + e.getCause());
            logger.addToLog("HabitsController Catch Error: " + e.toString(), true);
            return "errorpage";
        }
        return "habitspage";
    }

    private boolean isDataInvalid(Habit habit) {
        boolean isInvalid = true;
        if (DataValidation.isValid(habit.getName(), 3, 32)) {
            isInvalid = false;
            for (int i = 0; i < habit.getRatings().size(); i++) {
                if (!isInvalid) {
                    isInvalid = !DataValidation.isValid(habit.getRatings().get(i), 0, 10);
                }
            }
        }
        if (isInvalid) logger.addToLog("HabitsController isDataInvalid Error", true);
        return isInvalid;
    }

    private List<Habit> getUserHabits() {
        return userd.getUser().getHabits();
    }

    private void AddHabitToUser(Habit habit) {
        ArrayList<ArrayList> data = new ArrayList<>();
        ArrayList<Long> dates = new ArrayList();
        dates.add(getDate());
        habit.setDates(dates);
        userd.getUser().getHabits().add(habit);
        data.add(dates);
        logger.addToLog("Habit " + habit.getName() + " added for user " + userd.getUser().getUsername(), false);
    }

    private boolean checkForExistingHabit(Habit habit) {
        boolean habitExists = false;
        for (int i = 0; i < getUserHabits().size(); i++) {
            if (getUserHabits().get(i).getName().equals(habit.getName())) {
                habitExists = true;
            }
        }
        return habitExists;
    }

    private void updateExistingHabit(Habit habit) {
        for (int i = 0; i < getUserHabits().size(); i++) {
            if (getUserHabits().get(i).getName().equals(habit.getName())) {
                userd.getUser().getHabits().get(i).getDates().add(getDate());
                userd.getUser().getHabits().get(i).addRating(habit.getRatings().get(0));
                userService.saveUser(userd.getUser());
                System.out.println(userd.getUser().getHabits().get(0).getRatings());
            }
        }
        logger.addToLog("Habit " + habit.getName() + " updated for user " + userd.getUser().getUsername(), false);
    }

    private long getDate() {
        return System.currentTimeMillis();
    }
}