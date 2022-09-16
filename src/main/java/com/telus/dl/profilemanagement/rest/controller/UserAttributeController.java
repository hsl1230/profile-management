package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.attribute.AttributeDto;
import com.telus.dl.profilemanagement.dto.attribute.UserAttributeDto;
import com.telus.dl.profilemanagement.service.UserAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
            tags = {"User Public Attribute"},
            summary = "create a public user attribute"
    )
    @PostMapping("/public-attributes")
    public UserAttributeDto createPublicUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @Valid @RequestBody AttributeDto attributeDto) {
        return userAttributeService.createPublicUserAttribute(userProfileId, attributeDto);
    }

    @Operation(
            tags = {"User Public Attribute"},
            summary = "get a public user attribute by name"
    )
    @GetMapping("/public-attributes/{name}")
    public UserAttributeDto findPublicUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @PathVariable("name") String name) {
        return userAttributeService.findPublicUserAttributeById(userProfileId, name);
    }

    @Operation(
            tags = {"User Public Attribute"},
            summary = "get VerticalRolePermissions of a user profile"
    )
    @GetMapping("/public-attributes")
    public List<UserAttributeDto> findPublicUserAttributes(
            @PathVariable("userProfileId") String userProfileId) {
        return userAttributeService.findPublicUserAttributesByUserProfile(userProfileId);
    }

    @Operation(
            tags = {"User Private Attribute"},
            summary = "create a private user attribute",
            description = "private attributes are encrypted when saved in the db."
    )
    @PostMapping("/private-attributes")
    public void createPrivateUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @Valid @RequestBody AttributeDto attributeDto) {
        userAttributeService.createPrivateAttribute(userProfileId, attributeDto);
    }

    @Operation(
            tags = {"User Private Attribute"},
            summary = "get a private user attribute by name",
            description = "private attribute is decrypted when fetched from the db"
    )
    @GetMapping("/private-attributes/{name}")
    public UserAttributeDto findPrivateUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @PathVariable("name") String name) {
        return userAttributeService.findPrivateUserAttributeById(userProfileId, name);
    }

    @Operation(
            tags = {"User Private Attribute"},
            summary = "get private user attributes of a user profile",
            description = "private attributes are decrypted when fetched from the db"
    )
    @GetMapping("/private-attributes")
    public List<UserAttributeDto> findPrivateUserAttributes(
            @PathVariable("userProfileId") String userProfileId) {
        return userAttributeService.findPrivateUserAttributesByUserProfile(userProfileId);
    }

    @Operation(
            tags = {"User Private Attribute"},
            summary = "verify a private user attribute",
            description = "The value to be compared will be encrypted before compared with the encrypted value in the database."
    )
    @PutMapping("/private-attributes/{name}/verify")
    public void verifyPrivateUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @PathVariable("name") String name,
            @Valid @RequestBody Object value) {
        userAttributeService.verifyPrivateAttribute(userProfileId, name, value);
    }

    @Operation(
            tags = {"User Attribute"},
            summary = "get a user attribute by name",
            description = "include public and private attribute, private attribute will not be decrypted when fetched from the db"
    )
    @GetMapping("/{name}")
    public UserAttributeDto findUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @PathVariable("name") String name) {
        return userAttributeService.findUserAttributeById(userProfileId, name);
    }

    @Operation(
            tags = {"User Attribute"},
            summary = "get all user attributes of a user profile",
            description = "include public and private attributes, private attributes will not be decrypted when fetched from the db"
    )
    @GetMapping("")
    public List<UserAttributeDto> findAllUserAttributes(
            @PathVariable("userProfileId") String userProfileId) {
        return userAttributeService.findAllAttributesByUserProfile(userProfileId);
    }

    @Operation(
            tags = {"User Attribute"},
            summary = "delete a user attribute",
            description = "include private and public user attributes"
    )
    @DeleteMapping("/{name}")
    public void deleteUserAttribute(
            @PathVariable("userProfileId") String userProfileId,
            @PathVariable("name") String name
    ) {
        userAttributeService.deleteUserAttribute(userProfileId, name);
    }
}
