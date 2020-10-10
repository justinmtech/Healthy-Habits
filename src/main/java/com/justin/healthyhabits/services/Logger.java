package com.justin.healthyhabits.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private File file;
    private String time;
    private String date;
    private boolean error;

    public void addToLog(String message, boolean error) {
        this.error = error;
        setCurrentTime();
        setCurrentDate();
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
            file = new File("src/main/resources/logs/" + date + "-" + isError(error) + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void saveFile() {

    }

    private void getDate() {

    }

    private void deleteLogFile() {

    }

    private String isError(boolean value) {
        if (value)
            return "error";
        else
        return "log";
    }

    private void setCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        String time = currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        this.time = time;
    }

    private void setCurrentDate() {
        LocalDateTime currentDate = LocalDateTime.now();
        String date = currentDate.format(DateTimeFormatter.ofPattern("MM.dd.yyyy"));
        this.date = date;

    }




}
