package com.geneticpet.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.geneticpet.site"})

public class InheritedDiseasesRestApiApp {

    public static void main(String[] args) {
        SpringApplication.run(InheritedDiseasesRestApiApp.class, args);

    }
}

