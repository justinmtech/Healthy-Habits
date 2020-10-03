package com.justin.healthyhabits.services;

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

    public Optional<SiteUsers> getUser(int id) {
        return userRepository.findById(String.valueOf(id));
    }

    public void addUser(SiteUsers siteUser) {
        userRepository.save(siteUser);
    }

    public void saveUser(SiteUsers siteUser) {
        userRepository.save(siteUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
