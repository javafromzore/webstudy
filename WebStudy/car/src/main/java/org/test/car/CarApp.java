package org.test.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "org.test")
public class CarApp {
    public static void main(String[] args) {
        SpringApplication.run(CarApp.class, args);
    }
}