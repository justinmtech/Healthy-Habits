package com.justinmtech.healthyhabits.user;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {
    private static User user;
    private static Map<String, Habit> habits;

    @BeforeAll
    static void setup() {
        habits = new HashMap<>();
        habits.put("habit1", new Habit());
        habits.put("habit2", new Habit());
        habits.put("habit3", new Habit());

        user = new User("justin", "password", new HashMap<>());
        user.setHabits(habits);
    }

    @Test
    @Order(1)
    void getUsername() {
        assertEquals("justin", user.getUsername());
    }

    @Test
    @Order(2)
    void getPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    @Order(3)
    void getHabits() {
        assertTrue(user.getHabits().containsKey("habit1"));
        assertTrue(user.getHabits().containsKey("habit2"));
        assertTrue(user.getHabits().containsKey("habit3"));
    }

    @Test
    @Order(4)
    void setUsername() {
        user.setUsername("notjustin");
        assertEquals("notjustin", user.getUsername());
    }

    @Test
    @Order(5)
    void getRoles() {
        assertEquals("USER", user.getRoles());
    }

    @Test
    @Order(6)
    void setRoles() {
        user.setRoles("ADMIN");
        assertEquals("ADMIN", user.getRoles());
    }

    @Test
    @Order(7)
    void setHabits() {
        Map<String, Habit> habits = new HashMap<>();
        user.setHabits(habits);
        assertEquals(habits, user.getHabits());
    }

    @Test
    @Order(8)
    void setPassword() {
        user.setPassword("1 111  1 new password a a a");
        assertEquals("1 111  1 new password a a a", user.getPassword());
    }

    @Test
    @Order(9)
    void addHabit() {
        Habit habit = new Habit();
        habit.setName("my new habit");
        user.addHabit(habit);
        assertTrue(user.hasHabit("my new habit"));
    }

    @Test
    @Order(10)
    void removeHabit() {
        user.removeHabit("my new habit");
        assertFalse(user.hasHabit("my new habit"));
    }

    @Test
    void saveHabit() {
        Habit habit = new Habit();
        habit.setName("habit to update");
        user.addHabit(habit);
        assertTrue(user.hasHabit("habit to update"));
        habit.addDate(50L);
        habit.addRating(10);
        user.saveHabit(habit);
        assertTrue(user.hasHabit("habit to update"));
        Habit savedHabit = user.getHabits().get("habit to update");
        assertTrue(savedHabit.getDates().contains(50L));
        assertTrue(savedHabit.getRatings().contains(10));
    }

    @Test
    void hasHabit() {
        Habit habit = new Habit();
        habit.setName("bad habit");
        user.addHabit(habit);
        assertTrue(user.hasHabit(habit.getName()));
    }
}