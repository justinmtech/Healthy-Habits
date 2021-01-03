package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SuppressWarnings("ALL")
@Controller
public class GraphController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/graph")
    public String graph(Model model) {
        ArrayList<String> habitNames = new ArrayList<>();
        //ArrayList<Integer> habitRatingsData = new ArrayList<>();
        Map<Integer, LocalDate> habitRatingsData = new HashMap<>();
        User user = Objects.requireNonNull(sessionService.getAllSessions().stream().findFirst().orElse(null)).getSiteUser();

        for (int i = 0; i < user.getHabits().size(); i++) {
            habitNames.add(user.getHabits().get(i).getName());
            //habitRatingsData.add(user.getHabits().get(i).getRatings().get(i));
            habitRatingsData.put(user.getHabits().get(i).getRatings().get(i), user.getHabits().get(i).getDate());
        }

        habitNames.add("Test");
        habitRatingsData.put(10, LocalDate.of(2020, 12, 21));

        model.addAttribute("habitList", habitNames);
        model.addAttribute("habitDataList", habitRatingsData);

        return "graph";
    }

    private String getDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
        return formatter.format(now);
    }

    private List<String> getGraphTimeline() {
        LocalDate start = LocalDate.now().minusWeeks(2);
        LocalDate now = LocalDate.now();
        LocalDate end = LocalDate.now().plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        List<String> timeline = new ArrayList<>();
        timeline.add(start.format(formatter));
        timeline.add(now.format(formatter));
        timeline.add(end.format(formatter));

        return timeline;
    }
}
