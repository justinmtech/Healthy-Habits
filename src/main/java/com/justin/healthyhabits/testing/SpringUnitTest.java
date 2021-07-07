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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public void habitForm() {

    }

    @Test
    public void habitSubmit() {

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

    @Test
    public void doesThisReturnNull() throws Exception {
        userService.addUser(new User("Bob2", "password2"));
        List<String> habitList = new ArrayList<>();
        Map<Long, Integer> habitDataList = new HashMap<>();
        List<List<Object>> habitDataList2 = new ArrayList();
        User user = Objects.requireNonNull(sessionService.getAllSessions().stream().findFirst().orElse(null)).getSiteUser();

        try {
            for (int i = 0; i < user.getHabits().size(); i++) {
                habitList.add(user.getHabits().get(i).getName());
                ArrayList habitData = new ArrayList();
                for (int j = 0; j < user.getHabits().get(i).getDates().size(); j++) {
                    long date = user.getHabits().get(i).getDates().get(j);
                    //long dateInMilliseconds = convertDateToMilliseconds(date);
                    habitData.add(date);
                    habitData.add(user.getHabits().get(i).getRatings().get(j));
                }
                habitDataList2.add(habitData);
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    private long convertDateToMilliseconds(String date) throws ParseException {
        Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return newDate.getTime();
    }
}
