package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/logout")
    public String logout() {
        int sessionId = sessionService.getAllSessions().stream().findFirst().get().getSessionId();
        sessionService.deleteSession(sessionId);
        return "logout";
    }

}
