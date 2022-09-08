package com.telus.dl.profilemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.telus.core.errorhandling.EnableErrorHandling;

@EnableWebMvc
@EnableErrorHandling
@SpringBootApplication
public class ProfileManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfileManagementApplication.class, args);
    }

}
