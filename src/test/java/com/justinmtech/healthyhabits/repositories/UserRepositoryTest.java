package com.justinmtech.healthyhabits.repositories;

import com.justinmtech.healthyhabits.user.Habit;
import com.justinmtech.healthyhabits.user.User;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {
    private static User user;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    static void setup() {
        user = new User("username@gmail.com", " 1Q Q Q pasdfdfd w w ww w w111 1111");
    }

    @Test
    @Order(1)
    public void saveUser() {
        userRepository.save(user);
        assertTrue(userRepository.existsById("username@gmail.com"));

        ArrayList<Integer> ratings = new ArrayList<>();
        ratings.add(1);
        ratings.add(2);
        ratings.add(5);
        user.addHabit(new Habit("new habit 1", ratings, null));
        userRepository.save(user);
        Optional<User> fetchedUser = userRepository.findById(user.getUsername());
        assertTrue(fetchedUser.isPresent());
        assertTrue(user.hasHabit("new habit 1"));
        assertEquals(ratings, user.getHabits().get("new habit 1").getRatings());
        assertEquals(1, user.getHabits().get("new habit 1").getDates().size());
    }

    @Test
    @Order(2)
    public void getUser() {
        Optional<User> userOptional = userRepository.findById(user.getUsername());
        assertTrue(userOptional.isPresent());
    }

    @Test
    @Order(3)
    public void deleteUser() {
        userRepository.delete(user);
        assertFalse(userRepository.existsById(user.getUsername()));
    }

}