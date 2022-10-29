package com.justinmtech.healthyhabits.user;

import javax.persistence.*;
import java.util.ArrayList;
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

    public void addHabit(Habit habit) {
        ArrayList<Long> dates = new ArrayList<>();
        dates.add(System.currentTimeMillis());
        habit.setDates(dates);
        getHabits().put(habit.getName(), habit);
    }

    public Habit removeHabit(String habitId) {
        return getHabits().remove(habitId);
    }

    public boolean saveHabit(Habit habit) {
        boolean habitExists = getHabits().get(habit.getName()) != null;
        if (habitExists) {
            getHabits().get(habit.getName()).addDate(System.currentTimeMillis());
            getHabits().get(habit.getName()).addRating(habit.getRatings().get(0));
            return true;
        }
        return false;
    }

    public boolean hasHabit(String habitId) {
        return getHabits().containsKey(habitId);
    }

}
