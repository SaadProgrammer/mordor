package com.example.mordor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootConfiguration
@SpringBootApplication

public class MordorApplication {

    public static void main(String[] args) {

        SpringApplication.run(MordorApplication.class, args);
    }
}
