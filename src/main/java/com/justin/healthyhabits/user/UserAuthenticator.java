package com.justin.healthyhabits.user;

import com.justin.healthyhabits.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

public class UserAuthenticator {

    private String username;
    private String password;

    @Autowired
    UserService userService;

    public UserAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAuthenticator() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isAuthenticated() {
        Optional<SiteUsers> user = findUser();
        String password = user.get().getPassword();
            if (this.password == password) {
                return true;
            } else
                return false;
    }

    private Optional<SiteUsers> findUser() {
        return userService.getAllUsers().stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }
}
