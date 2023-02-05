package com.justinmtech.healthyhabits.user;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HabitTest {
    private static Habit habit;
    private static User user;

    @BeforeAll
    static void setup() {
        user = new User("username", "password");
        habit = new Habit("bad habit", new ArrayList<>(), new ArrayList<>());
        habit.setUser(user);
    }

    @Test
    @Order(1)
    void getDates() {
        assertTrue(habit.getDates().isEmpty());
    }

    @Test
    @Order(2)
    void setDates() {
        ArrayList<Long> dates = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        dates.add(currentTime);
        habit.setDates(dates);
        assertTrue(habit.getDates().contains(currentTime));
    }

    @Test
    @Order(3)
    void addDate() {
        long currentTime = System.currentTimeMillis() + 100L;
        habit.addDate(currentTime);
        assertTrue(habit.getDates().contains(currentTime));
    }

    @Test
    @Order(4)
    void getUser() {
        assertEquals(user, habit.getUser());
    }

    @Test
    @Order(5)
    void setUser() {
        User user = new User("bob", "mitchell");
        habit.setUser(user);
        assertEquals(user, habit.getUser());
    }

    @Test
    @Order(6)
    void getName() {
        assertEquals("bad habit", habit.getName());
    }

    @Test
    @Order(7)
    void setName() {
        habit.setName("new name");
        assertEquals("new name", habit.getName());
    }

    @Test
    @Order(8)
    void getRatings() {
        assertNotNull(habit.getRatings());
    }

    @Test
    @Order(9)
    void setRatings() {
        ArrayList<Integer> ratings = new ArrayList<>();
        ratings.add(1);
        ratings.add(2);
        ratings.add(5);
        ratings.add(10);
        habit.setRatings(ratings);
        assertEquals(ratings, habit.getRatings());
    }

    @Test
    @Order(10)
    void addRating() {
        int rating = 8;
        habit.addRating(rating);
        assertTrue(habit.getRatings().contains(rating));
    }

    @Test
    @Order(11)
    void getHabitType() {
        assertEquals("add", habit.getHabitType());
    }

    @Test
    @Order(12)
    void setHabitType() {
        habit.setHabitType("remove");
        assertEquals("remove", habit.getHabitType());
    }

    @Test
    @Order(13)
    void testToString() {
        assertEquals(habit.getName(), habit.toString());
    }
}