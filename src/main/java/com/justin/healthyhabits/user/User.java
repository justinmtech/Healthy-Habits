package com.justin.healthyhabits.user;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    private String username;
    private String password;
    private String roles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Habit> habits;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User() {
        this.roles = "USER";
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = "USER";
    }

    public User(String username, String password, List<Habit> habits) {
        this.username = username;
        this.password = password;
        this.habits = habits;
        this.roles = "USER";
    }

    public User (String username, String password, int userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.roles = "USER";
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
