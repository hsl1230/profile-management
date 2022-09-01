package com.telus.dl.profilemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ProfileManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileManagementApplication.class, args);
    }

}
