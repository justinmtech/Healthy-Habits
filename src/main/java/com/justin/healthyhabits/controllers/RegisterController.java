package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String ip = request.getRemoteAddr();
        System.out.println(session);
        System.out.println(ip);
        model.addAttribute("siteUser", new SiteUsers());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute SiteUsers siteUser, Model model) {
        model.addAttribute("siteUser", siteUser);

        userService.addUser(siteUser);

        return "home";
    }

}
