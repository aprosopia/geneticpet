package com.geneticpet.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.geneticpet.site"})

public class GeneticPetApp {

    public static void main(String[] args) {
        SpringApplication.run(GeneticPetApp.class, args);

    }
}

