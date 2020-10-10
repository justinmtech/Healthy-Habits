package com.justin.healthyhabits.user;

import javax.persistence.*;

@Entity
public class Habit {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int habitId;
    private String name;
    private int rating;
    @ManyToOne
    private User user;

    public User getSiteUsers() {
        return user;
    }

    public void setSiteUsers(User user) {
        this.user = user;
    }

    public Habit() {}

    public Habit(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}