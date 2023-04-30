package com.hospital.animal.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class HospitalAnimalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalAnimalApplication.class, args);
    }

}
