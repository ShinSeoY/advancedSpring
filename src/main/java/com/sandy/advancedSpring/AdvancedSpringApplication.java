package com.sandy.advancedSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AdvancedSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedSpringApplication.class, args);
    }

}
