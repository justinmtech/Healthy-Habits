package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.services.Logger;
import com.justin.healthyhabits.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.NoSuchElementException;

@Controller
public class LogoutController {
    private final Logger logger = new Logger();

    @Autowired
    SessionService sessionService;

    @GetMapping("/logout")
    public String logout() {
        try {
            int sessionId = sessionService.getAllSessions().stream().findFirst().orElseThrow().getSessionId();
            sessionService.deleteSession(sessionId);
            logger.addToLog("User logged out successfully", false);
        } catch (NoSuchElementException e) {
                String error = e.toString();
                logger.addToLog(error, true);
                return "errorpage";
            }
        return "logout";
    }

}
