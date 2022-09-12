package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.CreateUserVerticalEnablementRequest;
import com.telus.dl.profilemanagement.dto.UserVerticalEnablementDto;
import com.telus.dl.profilemanagement.service.UserVerticalEnablementService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/profile-management/verticals/{verticalId}/user-profiles/{userProfileId}/enablement")
@Validated
public class UserVerticalEnablementController {
    private final UserVerticalEnablementService userVerticalEnablementService;

    public UserVerticalEnablementController(UserVerticalEnablementService userVerticalEnablementService) {
        this.userVerticalEnablementService = userVerticalEnablementService;
    }

    @Operation(
            tags = {"User Vertical Enablement"},
            summary = "get the user vertical enablement"
    )
    @GetMapping
    public UserVerticalEnablementDto findUserVerticalEnablement(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("userProfileId") String userProfileId) {
        return userVerticalEnablementService.findUserVerticalEnablementById(verticalId, userProfileId);
    }

    @Operation(
            tags = {"User Vertical Enablement"},
            summary = "get the user vertical enablement"
    )
    @PutMapping
    public UserVerticalEnablementDto createUserVerticalEnablement(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("userProfileId") String userProfileId,
            @RequestBody CreateUserVerticalEnablementRequest createUserVerticalEnablementRequest) {
        return userVerticalEnablementService.createUserVerticalEnablement(verticalId, userProfileId, createUserVerticalEnablementRequest);
    }

    @Operation(
            tags = {"User Vertical Enablement"},
            summary = "delete the user vertical enablement"
    )
    @DeleteMapping
    public void deleteUserVerticalEnablement(
            @PathVariable("verticalId") String verticalId,
            @PathVariable("userProfileId") String userProfileId) {
        userVerticalEnablementService.deleteUserVerticalEnablement(verticalId, userProfileId);
    }
}
