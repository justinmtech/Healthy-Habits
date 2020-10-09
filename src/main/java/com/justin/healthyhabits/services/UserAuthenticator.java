package com.justin.healthyhabits.services;

import com.justin.healthyhabits.user.SiteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticator {
    private String username;
    private String password;

    @Autowired
    UserService userService;

    public String getCorrectPassword() {
        return correctPassword;
    }

    public void setCorrectPassword(String correctPassword) {
        this.correctPassword = correctPassword;
    }

    private String correctPassword;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserAuthenticator() {}

    public String getUsername() {
        return username;
    }

    private void userServiceGetPassword() {
        //correctPassword = userService.getAllUsers().stream().filter(u -> u.getUsername().equals(username)).findFirst().get().getPassword();
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


    public boolean isAuthenticated(SiteUsers user, String password, String username) {
        System.out.println(password);
        System.out.println(username);
            if (this.password.equals(user.getPassword()) && this.username.equals(user.getUsername())) {
                System.out.println("True!");
                return true;
            } else
                System.out.println("False");
                return false;
    }

/*    private Optional<SiteUsers> findUser() {
        //System.out.println(userService.getAllUsers().stream().filter(u -> u.getUsername().equals(username)).findFirst());
        //return userService.getAllUsers().stream().findAny();
        //return userService.getAllUsers().stream().filter(u -> u.getUsername().equals(username)).findFirst();
        System.out.println("test");
        userService.getAllUsers();
        System.out.println(userService.getAllUsers().isEmpty());
        return userService.getAllUsers().stream().findFirst();
    }*/
}
