package com.barraiser.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.barraiser"})
public class LibraryManagementSystem {
    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystem.class, args);
    }
}
