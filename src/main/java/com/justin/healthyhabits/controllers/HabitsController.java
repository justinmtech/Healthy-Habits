package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.DataValidation;
import com.justin.healthyhabits.services.LoggerService;
import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Habit;
import com.justin.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class HabitsController {

    @Autowired
    LoggerService logger;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

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
                if (checkForExistingHabit(habit)) updateExistingHabit(habit);
                else AddHabitToUser(habit);
                userService.saveUser(getSiteUser());
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
        if (DataValidation.isValid(habit.getName(), 3, 16)) {
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
        return getSiteUser().getHabits();
    }

    private void AddHabitToUser(Habit habit) {
        habit.setDates(new ArrayList<>(Arrays.asList(getDate())));
        getSiteUser().getHabits().add(habit);
        logger.addToLog("Habit " + habit.getName() + " added for user " + getSiteUser().getUsername(), false);
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
                getSiteUser().getHabits().get(i).getDates().add(getDate());
                getSiteUser().getHabits().get(i).addRating(habit.getRatings().get(0));
            }
        }
        logger.addToLog("Habit " + habit.getName() + " updated for user " + getSiteUser().getUsername(), false);
    }

    private String getDate() {
        LocalDateTime date = LocalDateTime.now();
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private User getSiteUser() throws NoSuchElementException {
        User user = new User();
        try {
            user = sessionService.getAllSessions().stream().findFirst().orElseThrow().getSiteUser();
        }
        catch (NoSuchElementException e) {
            logger.addToLog("User not found", true);
        }
        return user;
    }

    private void setHabitList(List<Habit> habitList) {
        getSiteUser().setHabits(habitList);
    }
}