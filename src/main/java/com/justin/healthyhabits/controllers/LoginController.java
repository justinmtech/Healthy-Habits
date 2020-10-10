package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.*;
import com.justin.healthyhabits.user.Session;
import com.justin.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Controller
public class LoginController {
    private Logger logger = new Logger();

    @Autowired
    UserAuthenticator userAuthenticator;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        try {
        if (DataValidation.isValid(user.getUsername(), 3, 16) &&
            DataValidation.isPasswordValid(user.getPassword(), 7, 128)) {
            userAuthenticator.setPasswordInput(user.getPassword());
            userAuthenticator.setUsernameInput(user.getUsername());
            model.addAttribute("user", user);

                User dbUser = userService.getAllUsers().stream().filter(u -> u.getUsername().equals(user.getUsername())).findFirst().orElseThrow();
                if (userAuthenticator.isAuthenticated(dbUser, dbUser.getPassword(), dbUser.getUsername())) {
                    Session session = new Session(dbUser);
                    sessionService.saveSession(session);
                    return "loginsuccessful";
                } else
                    return "errorpage";
        } else
            return "errorpage";

        } catch (NoSuchElementException | NullPointerException e) {
            String error = e.toString();
            logger.addToLog(error);
            return "errorpage";
            }
    }
}
