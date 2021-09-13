package com.justin.healthyhabits.services;

import com.justin.healthyhabits.user.CustomUserDetails;
import com.justin.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userService.getAllUsers().stream().filter(u -> u.getUsername().equals(s)).findAny();

        UserDetails userDetails = new CustomUserDetails(
                user.get().getUsername(),
                user.get().getPassword(),
                user.get().getRole(),
                new ArrayList<>(user.get().getAuthorities()),
                user.get().getHabits());

        return userDetails;
    }
}
