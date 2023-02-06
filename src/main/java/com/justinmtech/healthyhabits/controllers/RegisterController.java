package com.justinmtech.healthyhabits.controllers;

import com.justinmtech.healthyhabits.services.DataValidation;
import com.justinmtech.healthyhabits.services.UserService;
import com.justinmtech.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        try {
            if (DataValidation.isValid(user.getUsername(), 3, 320) &&
                    DataValidation.isPasswordValid(user.getPassword(), 7, 128)) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.addUser(user);
                return "registersuccessful";
            } else {
               return "errorpage";
            }
        } catch (Exception e) {
            System.out.println("Register failed: " + e);
            return "errorpage";
        }
    }

    @RequestMapping("registersuccessful")
    public String registerSuccessful() {
        return "registersuccessful";
    }
}
