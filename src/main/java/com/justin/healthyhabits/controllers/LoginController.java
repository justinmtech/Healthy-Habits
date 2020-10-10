package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.DataValidation;
import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserAuthenticator;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.Session;
import com.justin.healthyhabits.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Controller
public class LoginController {
    @Autowired
    UserAuthenticator userAuthenticator;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("siteUser", new SiteUser());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute SiteUser siteUser, Model model) {
        if (DataValidation.isValid(siteUser.getUsername(), 3, 16) &&
            DataValidation.isPasswordValid(siteUser.getPassword(), 7, 128)) {
            userAuthenticator.setPasswordInput(siteUser.getPassword());
            userAuthenticator.setUsernameInput(siteUser.getUsername());
            model.addAttribute("siteUser", siteUser);

            try {
                SiteUser user = userService.getAllUsers().stream().filter(u -> u.getUsername().equals(siteUser.getUsername())).findFirst().orElseThrow();
                if (userAuthenticator.isAuthenticated(user, siteUser.getPassword(), siteUser.getUsername())) {
                    Session session = new Session(user);
                    sessionService.saveSession(session);
                    return "loginsuccessful";
                } else
                    return "errorpage";
            } catch (NoSuchElementException | NullPointerException e) {
                System.out.println(e.toString());
                return "errorpage";
            }
        } else
        return "errorpage";
    }
}
