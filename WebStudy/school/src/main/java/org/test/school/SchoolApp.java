package org.test.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.test")
public class SchoolApp {
    public static void main(String[] args) {
        SpringApplication.run(SchoolApp.class, args);
    }
}