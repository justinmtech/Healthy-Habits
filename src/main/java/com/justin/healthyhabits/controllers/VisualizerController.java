package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Habits;
import com.justin.healthyhabits.user.Session;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VisualizerController {

/*    @Autowired
    UserService userService;
    @Autowired
    SessionService sessionService;*/

    @RequestMapping("/visualizer")
    public String showGraph(Model model) {
        List<Habits> habitList = new ArrayList<Habits>();
        SiteUsers siteUser = new SiteUsers("Justin2", "password2", habitList);
        habitList.add(new Habits("Gaming", 5));
        //sessionService.addSession(new Session(siteUser));
        //userService.addUser(siteUser);
        model.addAttribute("habits", siteUser.getHabits());
        model.addAttribute("siteUser", siteUser.getUserId());
        return "visualizer";
    }
}
