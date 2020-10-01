package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.user.Habits;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class VisualizerController {

    @RequestMapping("/visualizer")
    public String showGraph(Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        SiteUsers user = new SiteUsers("Casey", "password1");
        Habits habit = new Habits("Gaming", 3, dtf.format(now));
        Habits habit2 = new Habits("Social Media", 6, dtf.format(now));
        Habits habit3 = new Habits("Idleness", 7, dtf.format(now));
        Habits habit4 = new Habits("Nail Biting", 2, dtf.format(now));
        List<Habits> habits = new ArrayList<Habits>();
        habits.add(habit);
        habits.add(habit2);
        habits.add(habit3);
        habits.add(habit4);

        user.setHabits(habits);
        user.getHabits();

        model.addAttribute("users", user.getUserId());
        model.addAttribute("habits", user.getHabits());
        model.addAttribute("habitAmount", user.getHabits().size());

        return "visualizer";
    }
}
