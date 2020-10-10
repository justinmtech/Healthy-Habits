package com.justin.healthyhabits.services;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;

//TODO Create a logger that logs errors and puts them into /logs/file.txt

public class Logger {
    private File file;
    private Date date;
    private Time time;

    public void addToLog(String message) {
        createFile();

        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println(message);
            pw.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void createFile() {
        try {
            File file = new File("src/main/resources/logs/log.txt");
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




}
