package com.ccsw.tutorial_borrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TutorialBorrowApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialBorrowApplication.class, args);
    }

}
