package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Session;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("siteUser", new SiteUsers());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute SiteUsers siteUser, Model model) {
        model.addAttribute("siteUser", siteUser);
        userService.addUser(siteUser);
        sessionService.addSession(new Session(siteUser));
        System.out.println(sessionService.getAllSessions().size());
        return "visualizer";
    }
}
