package com.justinmtech.healthyhabits.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Habit {
    @Id
    private String name;
    private ArrayList<Integer> ratings;
    private ArrayList<Long> dates;
    @Transient
    private String habitType;
    @ManyToOne
    private User user;

    public Habit(String name, ArrayList<Integer> ratings, ArrayList<Long> dates) {
        this.name = name;
        this.ratings = ratings;
        this.dates = dates;
        this.habitType = "add";
    }

    public Habit(String name, ArrayList<Integer> ratings, ArrayList<Long> dates, String habitType) {
        this.name = name;
        this.ratings = ratings;
        this.dates = dates;
        this.habitType = habitType;
    }

    public Habit() {
        this.ratings = new ArrayList<>();
        this.dates = new ArrayList<>();
    }

    public List<Long> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Long> dates) {
        this.dates = dates;
    }

    public void addDate(Long date) {
    dates.add(date);
    }

    private List<Long> convertDatesToMilliseconds() throws ParseException {
        List<Long> convertedDates = new ArrayList<>();
        for (Long aLong : dates) {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(aLong));
            convertedDates.add(date.getTime());
        }
        return convertedDates;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }

    public void addRating(int rating) {
        this.ratings.add(rating);
    }

    public String getHabitType() {
        return habitType;
    }

    public void setHabitType(String habitType) {
        this.habitType = habitType;
    }

    @Override
    public String toString() {
        return name;
    }
}
