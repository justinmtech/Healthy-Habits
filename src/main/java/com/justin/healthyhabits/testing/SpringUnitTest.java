package com.justin.healthyhabits.testing;

import com.justin.healthyhabits.controllers.HabitsController;
import com.justin.healthyhabits.controllers.LoginController;
import com.justin.healthyhabits.controllers.RegisterController;
import com.justin.healthyhabits.repositories.SessionRepository;
import com.justin.healthyhabits.repositories.UserRepository;
import com.justin.healthyhabits.services.SessionService;
import com.justin.healthyhabits.services.UserAuthenticatorService;
import com.justin.healthyhabits.services.UserService;
import com.justin.healthyhabits.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SpringUnitTest {

    @Autowired
    private RegisterController registerController;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginController loginController;

    @Autowired
    private HabitsController habitsController;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserAuthenticatorService userAuthenticatorService;

    @Test
    public void registerControllerLoads() throws Exception {
        assertThat(registerController).isNotNull();
    }

    @Test
    public void registerUserSucceeds() throws Exception {
        userService.addUser(new User("Bob", "password"));
        userService.addUser(new User("Bob2", "password2"));
        Assertions.assertEquals(userService.getAllUsers().size(), 2);
    }

    @Test
    public void loginSucceeds() throws Exception {
        userAuthenticatorService.setUsernameInput("Bob");
        userAuthenticatorService.setPasswordInput("password");
        Assertions.assertTrue(userAuthenticatorService.isAuthenticated(new User("Bob", "password")));
    }

    @Test
    public void loginControllerLoads() throws Exception {
        assertThat(loginController).isNotNull();
    }

    @Test
    public void habitsControllerLoads() throws Exception {
        assertThat(habitsController).isNotNull();
    }
}
