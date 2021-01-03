package com.justin.healthyhabits.testing;

import com.justin.healthyhabits.user.Habit;
import com.justin.healthyhabits.user.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;

public class UserClassTesting {
    private final ArrayList<Integer> ratings = new ArrayList<>(Collections.singletonList(10));
    private final Habit habit = new Habit("Test Habit", ratings);
    private final ArrayList<Habit> habits = new ArrayList<>(Collections.singletonList(habit));
    private final User user = new User("Bob", "TestPassword", habits);

    @Test
    public void getUsernameTest() {
        Assertions.assertEquals("Bob", user.getUsername());
    }

    @Test
    public void getPasswordTest() {
        Assertions.assertEquals("TestPassword", user.getPassword());
    }

    @Test
    public void getHabitsTest() {
        Assertions.assertEquals(habits, user.getHabits());
    }

}
