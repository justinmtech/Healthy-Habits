package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.DataValidation;
import com.justin.healthyhabits.services.LoggerService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    LoggerService logger;

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        try {
            if (DataValidation.isValid(user.getUsername(), 3, 16) &&
                    DataValidation.isPasswordValid(user.getPassword(), 7, 128)) {
                userService.addUser(user);
                logger.addToLog("Registration successful for user " + user.getUsername(), false);
                return "registersuccessful";
            } else {
                return "errorpage";
            }
        } catch (NullPointerException e) {
            System.out.println("Register failed: " + e.toString());
            logger.addToLog("Registration failed for user " + user.getUsername(), true);
            return "errorpage";
        }
    }
}
