package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.HabitService;
import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Habits;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HabitsController {
    private List<Habits> habitList = new ArrayList<>();

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Autowired
    HabitService habitService;

    @GetMapping("/habits")
    public String habitForm(Model model) {
        model.addAttribute("habit", new Habits());
        return "habitspage";
    }

    @PostMapping("/habits")
    public String habitSubmit(@ModelAttribute Habits habit, Model model) {
        model.addAttribute("habit", habit);

        //get user based on IP
        //filter results based on IP
        getHabitList();
        addToHabitList(habit);
        setHabitList(habitList);

        habitService.addHabit(habit);
        userService.saveUser(getSiteUser().get());
        return "habitspage";
    }

    private List<Habits> getHabitList() {
        return habitService.getAllHabits();
    }

    private void addToHabitList(Habits habit) {
        habitService.addHabit(habit);
    }

    private Optional<SiteUsers> getSiteUser() {
        return userService.getAllUsers().stream().findFirst();
    }

    private void setHabitList(List<Habits> habitList) {
        getSiteUser().get().setHabits(habitList);
    }
}
