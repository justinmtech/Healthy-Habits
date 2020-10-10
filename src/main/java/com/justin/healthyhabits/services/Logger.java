package com.justin.healthyhabits.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private String time;
    private String date;

    public void addToLog(String message, boolean error) {
        setCurrentDateAndTime();
        createFile(error);

        try (FileWriter fw = new FileWriter("src/main/resources/logs/" + date + "-" + isError(error) + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            pw.println("[" + time + "] " + message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void createFile(boolean error) {
        try {
            File file = new File("src/main/resources/logs/" + date + "-" + isError(error) + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private String isError(boolean value) {
        if (value)
            return "error";
        else
        return "log";
    }

    private void setCurrentDateAndTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        String time = currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        String date = currentTime.format(DateTimeFormatter.ofPattern("MM.dd.yyyy"));
        this.time = time;
        this.date = date;
    }
}