package com.justin.healthyhabits.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    private static int min;
    private static int max;
    private static String inputString;
    private static int inputInt;

    private static boolean isTooLong(int min, int max) {
        if (inputString.length() < min || inputString.length() > max) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValueTooHigh(int min, int max) {
        if (inputInt < min || inputInt > max) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean hasSpecialCharacters() {
        Pattern acceptableChars = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = acceptableChars.matcher(inputString);
        return matcher.find();
    }

    public static boolean isValid(String input, int min, int max) {
        DataValidation.inputString = input;
        DataValidation.min = min;
        DataValidation.max = max;
        if (isTooLong(min, max) || hasSpecialCharacters()) {
            System.out.println(hasSpecialCharacters());
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValid(int input, int min, int max) {
        DataValidation.inputInt = input;
        if (isValueTooHigh(min, max) || hasSpecialCharacters()) {
            System.out.println(hasSpecialCharacters());
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPasswordValid(String input, int min, int max) {
        DataValidation.inputString = input;
        if (isTooLong(min, max)) {
            System.out.println(isTooLong(min, max));
            return false;
        } else {
            return true;
        }
    }
}
