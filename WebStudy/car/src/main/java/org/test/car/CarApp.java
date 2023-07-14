package org.test.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.test.*.api")
@SpringBootApplication(scanBasePackages = "org.test")
public class CarApp {
    public static void main(String[] args) {
        SpringApplication.run(CarApp.class, args);
    }
}