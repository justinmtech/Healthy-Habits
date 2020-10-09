package com.justin.healthyhabits.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    private String input;

    public DataValidation(String input) {
        this.input = input;
    }

    public boolean length(int min, int max) {
        if (input.length() < min || input.length() > max)
            return true;
        else
            return false;
    }

    public boolean parser() {
        Pattern acceptableChars = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = acceptableChars.matcher(input);
        boolean isAcceptable = matcher.find();
     if (isAcceptable)
         return true;
      else
         return false;
    }

    public void removeSpace() {
        input.trim();
    }


}
