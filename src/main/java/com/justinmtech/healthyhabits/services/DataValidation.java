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
        Pattern acceptableChars = Pattern.compile("[^A-Za-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = acceptableChars.matcher(inputString);
        return !matcher.find();
    }

    /**
     * Checks if string is too short/long or has special characters
     * @param input String input
     * @param min Minimum length
     * @param max Maximum length
     * @return True if string is within the min/max and has no special characters
     */
    public static boolean isValid(String input, int min, int max) {
        DataValidation.inputString = input;
        return isTooLong(min, max) && hasSpecialCharacters();
    }

    /**
     * Checks if int is too high or low
     * @param input String input
     * @param min Minimum length
     * @param max Maximum length
     * @return True if int is not too high or low
     */
    public static boolean isValid(int input, int min, int max) {
        DataValidation.inputInt = input;
        return !isValueTooHigh(min, max);
    }

    /**
     * Checks if a password string is too short or long
     * @param input String input
     * @param min Minimum length
     * @param max Maximum length
     * @return True if password string acceptable
     */
    public static boolean isPasswordValid(String input, int min, int max) {
        DataValidation.inputString = input;
        return isTooLong(min, max);
    }

    /**
     * @param habit Habit object
     * @return True if habit has no special characters, is within 3-32 characters long,
     * and its ratings are within 0-10
     */
    public static boolean isHabitValid(Habit habit) {
        if (!DataValidation.isValid(habit.getName(), 3, 32)) return false;
        for (int rating : habit.getRatings()) {
            if (!DataValidation.isValid(rating, 0, 10)) return false;
        }
        return true;
    }
}
