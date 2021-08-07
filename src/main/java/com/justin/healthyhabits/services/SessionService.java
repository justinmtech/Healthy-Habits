package com.justin.healthyhabits.services;

import com.justin.healthyhabits.repositories.SessionRepository;
import com.justin.healthyhabits.user.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    public List<Session> getAllSessions() {
        return (List<Session>) sessionRepository.findAll();
    }

    public Optional<Session> getSession(int id) {
        return sessionRepository.findById(id);
    }

    public void addSession(Session session) {
        sessionRepository.save(session);
    }

    public void saveSession(Session session) {
        sessionRepository.save(session);
    }

    public void deleteSession(int id) {
        sessionRepository.deleteById(id);
    }

}
