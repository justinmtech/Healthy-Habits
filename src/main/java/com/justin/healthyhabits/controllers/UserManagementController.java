package com.justin.healthyhabits.controllers;

import com.justin.healthyhabits.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/users")
public class UserManagementController {

    private static final List<User> USERS = Arrays.asList(
            new User("justin", "password1"),
            new User("tyler", "password2"),
            new User("mark", "password3")
    );

    @GetMapping
    public List<User> getAllUsers() {
        return USERS;
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(Integer userId) {
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
        System.out.println(String.format("%s %s", user, user));
    }

}
