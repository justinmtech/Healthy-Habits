package com.justin.healthyhabits.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Habits {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int habitId;
    private String name;
    private int rating;
    //private String dateAdded;

    public Habits() {}

    public Habits(String name, int rating) {
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
