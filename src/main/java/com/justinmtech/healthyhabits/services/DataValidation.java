package com.justinmtech.healthyhabits.services;

import com.justinmtech.healthyhabits.user.Habit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    private static String inputString;
    private static int inputInt;

    private static boolean isTooLong(int min, int max) {
        return inputString.length() >= min && inputString.length() <= max;
    }

    private static boolean isValueTooHigh(int min, int max) {
        return inputInt < min || inputInt > max;
    }

    private static boolean hasSpecialCharacters() {
        Pattern acceptableChars = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = acceptableChars.matcher(inputString);
        return !matcher.find();
    }

    public static boolean isValid(String input, int min, int max) {
        DataValidation.inputString = input;
        return isTooLong(min, max) && hasSpecialCharacters();
    }

    public static boolean isValid(int input, int min, int max) {
        DataValidation.inputInt = input;
        return !isValueTooHigh(min, max) && hasSpecialCharacters();
    }

    public static boolean isPasswordValid(String input, int min, int max) {
        DataValidation.inputString = input;
        return isTooLong(min, max);
    }

    public static boolean isHabitValid(Habit habit) {
        if (DataValidation.isValid(habit.getName(), 3, 32)) {
            for (int item : habit.getRatings()) {
                if (!DataValidation.isValid(item, 0, 10)) {
                    return false;
                }
            }
        }
        return true;
    }
}
