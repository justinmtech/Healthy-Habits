package com.justinmtech.healthyhabits.services;

import com.justinmtech.healthyhabits.user.Habit;
import com.justinmtech.healthyhabits.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    private static User user;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @BeforeAll
    static void setup() {
        user = new User("tyler", "password");
    }

    @Test
    @Order(1)
    void addUser() {
        userService.addUser(user);
        assertTrue(userService.getUser(user.getUsername()).isPresent());
    }

    @Test
    @Order(2)
    void getUser() {
        Optional<User> fetchedUser = userService.getUser(user.getUsername());
        assertTrue(fetchedUser.isPresent());
        assertEquals("tyler", fetchedUser.get().getUsername());
        String password = fetchedUser.get().getPassword();
        assertEquals(user.getPassword(), password);
    }

    @Test
    @Order(3)
    void saveUser() {
        ArrayList<Integer> ratings = new ArrayList<>();
        ratings.add(5);
        user.addHabit(new Habit("test", ratings, null));
        userService.saveUser(user);
        Optional<User> fetchedUser = userService.getUser(user.getUsername());
        assertTrue(fetchedUser.isPresent());
        assertTrue(fetchedUser.get().hasHabit("test"));
    }

    @Test
    @Order(4)
    void deleteUser() {
        userService.deleteUser(user.getUsername());
        assertTrue(userService.getUser(user.getUsername()).isEmpty());
    }

}