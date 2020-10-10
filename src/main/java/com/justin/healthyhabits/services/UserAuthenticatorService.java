package com.justin.healthyhabits.services;

import com.justin.healthyhabits.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticatorService {
    private String usernameInput;
    private String passwordInput;

    public UserAuthenticatorService() {}

    public void setUsernameInput(String usernameInput) {
        this.usernameInput = usernameInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }

    public boolean isAuthenticated(User user) {
            if (this.passwordInput.equals(user.getPassword()) && this.usernameInput.equals(user.getUsername())) {
                System.out.println("True!");
                return true;
            } else
                System.out.println("False");
                return false;
    }
}
