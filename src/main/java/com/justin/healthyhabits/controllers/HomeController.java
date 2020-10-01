package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        SiteUsers user1 = new SiteUsers("Baka", "passwordd5");
        List<SiteUsers> userList = new ArrayList<SiteUsers>();
        userList.add(user1);
        model.addAttribute("users", userList.size());
        return "home";
    }
}
