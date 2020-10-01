package com.justin.healthyhabits.services;

import com.justin.healthyhabits.user.Habits;
import com.justin.healthyhabits.user.SiteUsers;
import com.justin.healthyhabits.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<SiteUsers> getAllUsers() {
        List<SiteUsers> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

    public Optional<SiteUsers> getUser(String id) {
        return userRepository.findById(id);
    }

    public void addUser(SiteUsers siteUser) {
        userRepository.save(siteUser);
    }

    public void saveUser(String id, SiteUsers siteUser) {
        userRepository.save(siteUser);
    }

    public void deleteUser(String id, SiteUsers siteUser) {
        userRepository.deleteById(id);
    }
}
