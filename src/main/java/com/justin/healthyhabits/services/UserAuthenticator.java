package com.justin.healthyhabits.services;

import com.justin.healthyhabits.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticator {
    private String usernameInput;
    private String passwordInput;

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

    public String getUsernameInput() {
        return usernameInput;
    }

    private void userServiceGetPassword() {
        //correctPassword = userService.getAllUsers().stream().filter(u -> u.getUsername().equals(username)).findFirst().get().getPassword();
    }

    public void setUsernameInput(String usernameInput) {
        this.usernameInput = usernameInput;
    }

    public String getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }


    public boolean isAuthenticated(SiteUser user, String password, String username) {
        System.out.println(password);
        System.out.println(username);
            if (this.passwordInput.equals(user.getPassword()) && this.usernameInput.equals(user.getUsername())) {
                System.out.println("True!");
                return true;
            } else
                System.out.println("False");
                return false;
    }
}
