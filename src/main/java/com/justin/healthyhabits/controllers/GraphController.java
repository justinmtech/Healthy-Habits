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
        ArrayList<String> habitList = new ArrayList<>();
        Map<Long, Integer> habitDataList = new HashMap<Long, Integer>();
        ArrayList<ArrayList<Object>> habitDataList2 = new ArrayList();
        User user = Objects.requireNonNull(sessionService.getAllSessions().stream().findFirst().orElse(null)).getSiteUser();

        for (int i = 0; i < user.getHabits().size(); i++) {
            habitList.add(user.getHabits().get(i).getName());
            habitDataList2.add(new ArrayList<>(Arrays.asList(user.getHabits().get(i).getDate(), user.getHabits().get(i).getRatings().get(0))));
        }

        //habitList.add("Test");
        //habitList.add("Test2");

        //habitDataList.put(15000000000L, 2);
        //habitDataList.put(15500000000L, 5);

        //habitDataList2.add(new ArrayList<>(Arrays.asList(15000000000L, 2)));
        //habitDataList2.add(new ArrayList<>(Arrays.asList(15500000000L, 5)));

        System.out.println(habitDataList2);

        model.addAttribute("habitList", habitList);
        model.addAttribute("habitDataList", habitDataList2);

        return "graph";
    }

    private String getDate() {
        LocalDate now = LocalDate.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return date;
    }

    private List<String> getGraphTimeline() {
        LocalDate start = LocalDate.now().minusWeeks(2);
        LocalDate now = LocalDate.now();
        LocalDate end = LocalDate.now().plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        List<String> timeline = new ArrayList<>();
        timeline.add(start.format(formatter));
        timeline.add(now.format(formatter));
        timeline.add(end.format(formatter));

        return timeline;
    }
}
