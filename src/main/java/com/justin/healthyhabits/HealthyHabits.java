package com.justin.healthyhabits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO Implement Spring Security for page viewing permissions
//TODO Implement logins and registers with Spring Security
//TODO Add a feature for removing habits
//TODO Make data persistent
//TODO Make site easier to navigate

@SpringBootApplication
public class HealthyHabits {

    public static void main(String[] args) {
        SpringApplication.run(HealthyHabits.class, args);
    }

}
