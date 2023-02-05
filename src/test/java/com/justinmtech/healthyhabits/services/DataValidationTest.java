package com.justinmtech.healthyhabits.services;

import com.justinmtech.healthyhabits.user.Habit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataValidationTest {

    @Test
    void isValid() {
        assertFalse(DataValidation.isValid(100, 2, 5));
        assertFalse(DataValidation.isValid(11, 0, 10));
        assertFalse(DataValidation.isValid(54, 55, 55));
        assertTrue(DataValidation.isValid(25, 25, 25));
        assertTrue(DataValidation.isValid(24, 24, 25));
        assertTrue(DataValidation.isValid(10, 9, 11));
        assertTrue(DataValidation.isValid("verylongusername", 5, 16));
        assertFalse(DataValidation.isValid("verylongusername", 5, 15));
        assertFalse(DataValidation.isValid("verylongusern!ame", 12, 25));
        assertFalse(DataValidation.isValid("very", 5, 16));
    }

    @Test
    void isPasswordValid() {
        String password = "s#6M#Z*68b@I4";
        String passwordLong = "BP-XqH#wqM7%s'[7}QK!d>*%ZQc#=8kA:;J2Jy#$>;yR`n`xUuy]@?y9A<N,-eA7?ZQ:_f\\ehst+UXfB%&Us~w:2%Sxa`[H_r)~z+2ZfsPy];n?mT\\spzAQfpxg6$!L8jAn@S49Yb\\Z9'A:pNw5?\\7gB<\\bsJ*/Kj[)$6#!~h%Zw2EcB}K9WA-YcSL2:'R2ghdV\\<L/<4$k>S)/`X.3QJCxuCpt#D{!pYsRA+m@?hm.)~jhXDTA6fs$~NG4Kqv9Yes{wN[P?/:9$>c{MHvbP//dMa%{'\"tGE[zx?Uv[C+/e(W'*w,*zx,h4g7z:`Qp'.SHSZ>&GX;6{YF@4XSw3Rb;p}S4Qn?qN7RX7r7pK_:-m/-zK'R\\$<)Y6z)nEza>xW^?&RDJ&!~c9-gQ3xc;}Xx+[*z,a3\"<tfw~(U}Y*6e,sYHx{9#B\"-s4D_p8zTSN'TH4>A*]rs`,fphGa\"fY{}wec>D6PAU.jhfdx4~pvE2!$}h!\"GpQdqR!B,4MG;+Ms*";
        assertTrue(DataValidation.isPasswordValid(password, 6, 320));
        assertFalse(DataValidation.isPasswordValid(passwordLong, 6, 320));
        assertTrue(DataValidation.isPasswordValid("normal password", 7, 250));
    }

    @Test
    void isHabitValid() {
        Habit habit = new Habit();
        habit.setName("bad habit!");
        ArrayList<Integer> ratings = new ArrayList<>();
        ratings.add(0);
        ratings.add(5);
        ratings.add(10);
        ratings.add(11);
        habit.setRatings(ratings);
        habit.setDates(new ArrayList<>());
        assertFalse(DataValidation.isHabitValid(habit));
        habit.setName("good habit");
        assertFalse(DataValidation.isHabitValid(habit));
        habit.getRatings().remove(3);
        assertTrue(DataValidation.isHabitValid(habit));
    }
}