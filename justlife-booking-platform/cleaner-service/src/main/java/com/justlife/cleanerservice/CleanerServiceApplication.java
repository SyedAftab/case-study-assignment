package com.justlife.cleanerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @author Aftab
 */
@SpringBootApplication
@EnableFeignClients
public class CleanerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleanerServiceApplication.class, args);
    }
}
