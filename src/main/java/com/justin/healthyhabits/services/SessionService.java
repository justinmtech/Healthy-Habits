package com.justin.healthyhabits.services;

import com.justin.healthyhabits.user.Session;
import com.justin.healthyhabits.user.SessionRepository;
import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    public List<Session> getAllSessions() {
        List<Session> activeSessions = new ArrayList<>();
        sessionRepository.findAll().forEach(activeSessions::add);
        return activeSessions;
    }

    public Optional<Session> getSession(String id) {
        return sessionRepository.findById(id);
    }

    public void addSession(Session session) {
        sessionRepository.save(session);
    }

    public void updateSession(Session session) {
        sessionRepository.save(session);
    }

    public void deleteSession(String id) {
        sessionRepository.deleteById(id);
    }

}
