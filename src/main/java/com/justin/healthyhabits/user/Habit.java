package com.justin.healthyhabits.user;

import javax.persistence.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
@Entity
public class Habit {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int habitId;
    private String name;
    private ArrayList<Integer> ratings;
    private long date;
    @ManyToOne
    private User user;

    public Habit(String name, ArrayList<Integer> ratings) {
        this.name = name;
        this.ratings = ratings;
        setDate();
    }

    public Habit() {
        setDate();
    }

    public Long getDate() {
        return date;
    }

    public void setDate() {
        //LocalDate date = LocalDate.now();
        this.date = java.lang.System.currentTimeMillis();
        //date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        //Timestamp timestamp = Timestamp.valueOf(String.valueOf(date));
        //System.out.println(Long.parseLong(timestamp.toString()));
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
