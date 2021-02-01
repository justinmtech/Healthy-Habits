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
        boolean isValid = false;
        try {
            if (DataValidation.isValid(habit.getName(), 3, 16)) {
                isValid = true;
                for (int i = 0; i < habit.getRatings().size(); i++) {
                    if (isValid) {
                            isValid = DataValidation.isValid(habit.getRatings().get(i), 0, 10);
                    }
                }
                setHabitList(getHabitList());
                for (int i = 0; i < getHabitList().size(); i++) {
                if (getHabitList().get(i).equals(habit.getName())) {
                    return "errorpage";
                    }
                }
                addToHabitList(habit);
                userService.saveUser(getSiteUser());
                logger.addToLog("Habit " + habit.getName() + " added for user " + getSiteUser().getUsername(), false);
                return "habitspage";
            } else
                logger.addToLog("Habit Error", true);
                return "errorpage";
        } catch (NullPointerException | NoSuchElementException e) {
            String error = e.toString();
            logger.addToLog("Habit Error: " + error, true);
            return "errorpage";
        }
    }

    private List<Habit> getHabitList() {
        return getSiteUser().getHabits();
    }

    private void addToHabitList(Habit habit) {
        getSiteUser().getHabits().add(habit);
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