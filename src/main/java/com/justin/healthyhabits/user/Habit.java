package com.justin.healthyhabits.user;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@SuppressWarnings("unused")
@Entity
public class Habit {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int habitId;
    private String name;
    private ArrayList<Integer> ratings;
    @ManyToOne
    private User user;
    private LocalDate date;

    public Habit(String name, ArrayList<Integer> ratings) {
        this.name = name;
        this.ratings = ratings;
        setDate();
    }

    public Habit() {
        setDate();
        System.out.println(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate() {
        this.date = LocalDate.now();
    }

    public User getSiteUsers() {
        return user;
    }

    public void setSiteUsers(User user) {
        this.user = user;
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

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }
}
