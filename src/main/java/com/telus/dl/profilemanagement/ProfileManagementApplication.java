package com.telus.dl.profilemanagement;

import com.telus.core.modelmapping.EnableModelMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.telus.core.errorhandling.EnableErrorHandling;

@EnableWebMvc
@EnableErrorHandling
@EnableModelMapping
@SpringBootApplication
public class ProfileManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfileManagementApplication.class, args);
    }

}
