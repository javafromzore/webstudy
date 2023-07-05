package org.test.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.test")
public class WXApp {
    public static void main(String[] args) {
        SpringApplication.run(WXApp.class, args);
    }
}