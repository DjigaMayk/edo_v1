package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EdoIntegrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdoIntegrationApplication.class, args);
    }
}