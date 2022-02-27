package com.justin.healthyhabits.services;

import com.justin.healthyhabits.security.CustomUserDetails;
import com.justin.healthyhabits.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.getUser(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user.map(CustomUserDetails::new).get();
    }

    public String getUsername() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userService.getUser(userDetails.getUsername());
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + userDetails.getUsername());
        }
        return user.get();
    }
}
