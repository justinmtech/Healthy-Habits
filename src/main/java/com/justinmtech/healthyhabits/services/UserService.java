package com.justinmtech.healthyhabits.services;

import com.justinmtech.healthyhabits.repositories.UserRepository;
import com.justinmtech.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }
    public void addUser(User user) {
        userRepository.save(user);
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
