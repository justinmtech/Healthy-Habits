package com.justin.healthyhabits.user;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;
    private String roles;
    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, Habit> habits;

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

    public User(String username, String password, Map<String, Habit> habits) {
        this.username = username;
        this.password = password;
        this.habits = habits;
        this.roles = "USER";
    }

    public Map<String, Habit> getHabits() {
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

    public void setHabits(Map<String, Habit> habits) {
        this.habits = habits;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
