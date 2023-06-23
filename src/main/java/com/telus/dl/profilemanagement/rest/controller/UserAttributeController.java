package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.attribute.AttributeDto;
import com.telus.dl.profilemanagement.dto.attribute.UserAttributeDto;
import com.telus.dl.profilemanagement.service.UserAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profile-management/user-profiles/{userProfileId}/attributes")
@Validated
public class UserAttributeController {
    private final UserAttributeService userAttributeService;

    public UserAttributeController(UserAttributeService userAttributeService) {
        this.userAttributeService = userAttributeService;
    }

    @Operation(
            tags = {"User Attribute"},
            summary = "create a user attribute"
    )
    @PostMapping("")
    public UserAttributeDto createUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @Valid @RequestBody AttributeDto attributeDto) {
        return userAttributeService.createUserAttribute(userProfileId, attributeDto);
    }

    @Operation(
            tags = {"User Attribute"},
            summary = "get an user attribute by name"
    )
    @GetMapping("/{name}")
    public UserAttributeDto findUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @PathVariable("name") String name,
            @Parameter(
                name = "verticalId",
                in = ParameterIn.QUERY,
                description = "vertical id of the attribute",
                required = false
              ) @RequestParam(value = "verticalId", required = false) String verticalId,
            @Parameter(
                name = "decrypt",
                in = ParameterIn.QUERY,
                description = "whether there is a need to decrypt the value, default is false",
                required = false
              ) @RequestParam(value = "decrypt", required = false) boolean decrypt) {
        return userAttributeService.findUserAttributeById(userProfileId, name, verticalId, decrypt);
    }

    @Operation(
            tags = {"User Attribute"},
            summary = "get all user attributes of a user profile"
    )
    @GetMapping("")
    public List<UserAttributeDto> findUserAttributes(
            @PathVariable("userProfileId") String userProfileId,
            @Parameter(
                name = "decrypt",
                in = ParameterIn.QUERY,
                description = "whether there is a need to decrypt the value, default is false",
                required = false
              ) @RequestParam(value = "decrypt", required = false) boolean decrypt) {
        return userAttributeService.findAllAttributesByUserProfile(userProfileId, decrypt);
    }

    @Operation(
            tags = {"User Attribute"},
            summary = "verify a user attribute"
    )
    @PutMapping("/{name}/verify")
    public void verifyPrivateUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @PathVariable("name") String name,
            @Parameter(
                name = "verticalId",
                in = ParameterIn.QUERY,
                description = "vertical id of the attribute",
                required = false
              ) @RequestParam(value = "verticalId", required = false) String verticalId,
            @Valid @RequestBody Object value) {
        userAttributeService.verifyAttribute(userProfileId, name, verticalId, value);
    }

    @Operation(
            tags = {"User Attribute"},
            summary = "delete an user attribute"
    )
    @DeleteMapping("/{name}")
    public void deleteUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @PathVariable("name") String name
    ) {
        userAttributeService.deleteUserAttribute(userProfileId, name);
    }
}
