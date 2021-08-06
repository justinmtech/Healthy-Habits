package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.LoggerService;
import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

@SuppressWarnings("ALL")
@Controller
public class GraphController {

    @Autowired
    LoggerService logger;

    @Autowired
    SessionService sessionService;

    @GetMapping("/graph")
    public String graph(Model model) throws ParseException {
        addModelAttributes(model);
        return "graph";
    }

    private User getSiteUser() throws NoSuchElementException {
        User user = new User();
        try {
            user = sessionService.getAllSessions().stream().findFirst().orElseThrow().getSiteUser();
        }
        catch (NoSuchElementException e) {
            logger.addToLog("User not found", true);
        }
        return user;
    }

    private void addModelAttributes(Model model) {
        model.addAttribute("userHabits", getSiteUser().getHabits());
    }

    private long convertDateToMilliseconds(String date) throws ParseException {
        Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return newDate.getTime();
        }
}
