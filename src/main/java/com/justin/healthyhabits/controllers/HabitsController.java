package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.DataValidation;
import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Habit;
import com.justin.healthyhabits.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HabitsController {

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @GetMapping("/habits")
    public String habitForm(Model model) {
        model.addAttribute("habit", new Habit());
        return "habitspage";
    }

    @PostMapping("/habits")
    public String habitSubmit(@ModelAttribute Habit habit, Model model) {
        model.addAttribute("habit", habit);

        if (DataValidation.isValid(habit.getName(), 3, 16) &&
            DataValidation.isValid(habit.getRating(), 0, 10)) {
                setHabitList(getHabitList());
                addToHabitList(habit);
                userService.saveUser(getSiteUser());
            } else {
            return "errorpage";
        }
        return "habitspage";
    }

    private List<Habit> getHabitList() {
        return getSiteUser().getHabits();
    }

    private void addToHabitList(Habit habit) {
        getSiteUser().getHabits().add(habit);
    }

    private SiteUser getSiteUser() {
        return sessionService.getAllSessions().stream().findFirst().get().getSiteUser();
    }

    private void setHabitList(List<Habit> habitList) {
        getSiteUser().setHabits(habitList);
    }
}