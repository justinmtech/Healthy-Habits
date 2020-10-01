package com.justin.healthyhabits.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class SiteUsers {

    @Id
    private String userId;
    private String password;
    @OneToMany
    private List<Habits> habits;

    public SiteUsers() {}

    public SiteUsers(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public List<Habits> getHabits() {
        return habits;
    }

    public void setHabits(List<Habits> habits) {
        this.habits = habits;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
