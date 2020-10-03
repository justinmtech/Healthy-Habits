package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Session;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new SiteUsers());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(Model model) {
        SiteUsers siteUser = new SiteUsers();
        model.addAttribute("register", siteUser);
        sessionService.addSession(new Session(siteUser));

        userService.addUser(siteUser);
        return "home";
    }

}
