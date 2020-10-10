package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.DataValidation;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("siteUser", new SiteUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute SiteUser siteUser, Model model) {
        model.addAttribute("siteUser", siteUser);
        if (DataValidation.isValid(siteUser.getUsername(), 3, 16) &&
            DataValidation.isPasswordValid(siteUser.getPassword(), 7, 128)) {
            userService.addUser(siteUser);
            return "registersuccessful";
        } else {
            return "errorpage";
        }
    }
}
