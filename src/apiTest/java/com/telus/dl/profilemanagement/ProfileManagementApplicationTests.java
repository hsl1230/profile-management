package com.telus.dl.profilemanagement;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.telus.dl.profilemanagement.rest.controller.UserProfileController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProfileManagementApplicationTests {
    @Autowired
    private UserProfileController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }
}
