package com.justin.healthyhabits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO Make habits added detect if a habit already exists, and add a new entry for it if so

@SpringBootApplication
public class HealthyHabits {

    public static void main(String[] args) {
        SpringApplication.run(HealthyHabits.class, args);
    }

}
