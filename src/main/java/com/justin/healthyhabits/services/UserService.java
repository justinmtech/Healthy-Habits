package com.justin.healthyhabits.services;

import com.justin.healthyhabits.repositories.UserRepository;
import com.justin.healthyhabits.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<SiteUser> getAllUsers() {
        List<SiteUser> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(allUsers::add);
        return allUsers;
    }

    public Optional<SiteUser> getUser(int id) {
        return userRepository.findById(id);
    }

    public void addUser(SiteUser siteUser) {
        userRepository.save(siteUser);
    }

    public void saveUser(SiteUser siteUser) {
        userRepository.save(siteUser);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
