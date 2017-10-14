package com.iadmintech.sample.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.iadmintech.sample.module")
public class SampleServiceRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleServiceRestApplication.class, args);
    }
}
