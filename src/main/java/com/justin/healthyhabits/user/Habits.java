package com.justin.healthyhabits.user;

import javax.persistence.*;

@Entity
public class Habits {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int habitId;
    private String name;
    private int rating;
    @ManyToOne
    private SiteUsers siteUsers;

    public SiteUsers getSiteUsers() {
        return siteUsers;
    }

    public void setSiteUsers(SiteUsers siteUsers) {
        this.siteUsers = siteUsers;
    }

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
