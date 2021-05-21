package com.justin.healthyhabits.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
@Entity
public class Habit {
    //private int habitId;
    @Id //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String name;
    private ArrayList<Integer> ratings;
    private ArrayList<String> dates;
    @ManyToOne
    private User user;

    public Habit(String name, ArrayList<Integer> ratings, ArrayList<String> dates) {
        this.name = name;
        this.ratings = ratings;
        this.dates = dates;
    }

    public Habit() {}

    public List<String> getDates() {
        return dates;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    public void addDate(String date) {
    this.dates.add(date);
    }

    public List<Long> convertDatesToMilliseconds() throws ParseException {
        List<Long> convertedDates = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dates.get(i));
            convertedDates.add(date.getTime());
        }
        return convertedDates;
    }

    public User getSiteUsers() {
        return user;
    }

    public void setSiteUsers(User user) {
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
}
