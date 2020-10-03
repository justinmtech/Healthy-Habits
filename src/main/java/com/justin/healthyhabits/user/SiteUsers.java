package com.justin.healthyhabits.user;

import javax.persistence.*;
import java.util.List;

@Entity
public class SiteUsers {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    private String username;
    private String password;
    @OneToMany
    private List<Habits> habits;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SiteUsers() {}

    public SiteUsers(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SiteUsers(String username, String password, List<Habits> habits) {
        this.username = username;
        this.password = password;
        this.habits = habits;
    }

    public List<Habits> getHabits() {
        return habits;
    }

    public void setHabits(List<Habits> habits) {
        this.habits = habits;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
