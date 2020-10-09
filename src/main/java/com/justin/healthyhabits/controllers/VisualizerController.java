package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VisualizerController {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @RequestMapping("/visualizer")
    public String showGraph(Model model) {
        //userService.getAllUsers();
        //Optional<SiteUsers> siteUser = userService.getAllUsers().stream().findFirst();
        int userId = sessionService.getAllSessions().stream().findAny().get().getSiteUser().getUserId();

        model.addAttribute("totalHabits", userService.getUser(userId).get().getHabits().size());
        model.addAttribute("habits", userService.getUser(userId).get().getHabits());
        model.addAttribute("siteUser", userService.getUser(userId));

/*        Map<String, Integer> habitMap = new HashMap<>();
        for (int i = 0; i < habitList.size(); i++) {}*/

        return "visualizer";
    }
}
