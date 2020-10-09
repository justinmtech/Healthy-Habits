package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.HabitService;
import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserAuthenticator;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Session;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UserAuthenticator userAuthenticator;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Autowired
    HabitService habitService;


    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("siteUser", new SiteUsers());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute SiteUsers siteUser, Model model) {
        userAuthenticator.setPassword(siteUser.getPassword());
        userAuthenticator.setUsername(siteUser.getUsername());
        model.addAttribute("siteUser", siteUser);

        SiteUsers user = new SiteUsers();

        Optional<SiteUsers> siteUsersOptional = userService.getAllUsers().stream().filter(u -> u.getUsername().equals(siteUser.getUsername())).findFirst();
        if (siteUsersOptional.isPresent()) {
            user.setUsername(siteUsersOptional.get().getUsername());
            user.setPassword(siteUsersOptional.get().getPassword());
            user.setHabits(habitService.getAllHabits());
            System.out.println("Database username: " + user.getUsername());
            System.out.println("Database pw: " + user.getPassword());
            System.out.println("Database habits: " + user.getHabits().size());
            //System.out.println("Habits size: " + user.getHabits().size());
        }

        if (userAuthenticator.isAuthenticated(user, siteUser.getPassword(), siteUser.getUsername())) {
            Session session = new Session();
            session.setSiteUser(user);
            userService.saveUser(user);
            sessionService.saveSession(session);
            System.out.println("Logged in!");
            return "visualizer";
        } else
            System.out.println("Login failed!");
        return "home";
    }
}
