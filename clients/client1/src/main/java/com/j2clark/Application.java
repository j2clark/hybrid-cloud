package com.j2clark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
//@EnableCircuitBreaker
@SpringBootApplication
public class Application {

    // todo: sdk component scan issue - moved this class to lowest common package for time being

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

