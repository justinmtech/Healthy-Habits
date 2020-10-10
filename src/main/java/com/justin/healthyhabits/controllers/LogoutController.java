package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.NoSuchElementException;

@Controller
public class LogoutController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/logout")
    public String logout() {
        try {
            int sessionId = sessionService.getAllSessions().stream().findFirst().orElseThrow().getSessionId();
            sessionService.deleteSession(sessionId);
        } catch (NoSuchElementException e) {
                System.out.println(e.toString());
                return "errorpage";
            }
        return "logout";
    }

}
