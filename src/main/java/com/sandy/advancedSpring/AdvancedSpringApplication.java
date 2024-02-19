package com.sandy.advancedSpring;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableBatchProcessing
@EnableScheduling
public class AdvancedSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedSpringApplication.class, args);
    }

}
