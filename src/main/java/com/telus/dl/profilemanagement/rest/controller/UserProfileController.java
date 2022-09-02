package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.dto.*;
import com.telus.dl.profilemanagement.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile-management/user-profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Operation(
            tags = {"User Profile"},
            summary = "get a list of user profiles related to the specified myTelusId",
            description = "a list mixing of primary user profiles and sub user profiles.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list of user profiles including primary user profiles and sub user profiles owned by a my telus user",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    anyOf = {PrimaryUserProfileDto.class, SubUserProfileDto.class},
                                                    type = "object"
                                            )
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{myTelusId}")
    public List<AbstractUserProfileDto> getUserProfilesByMyTelusId(
            @Parameter(in = ParameterIn.PATH, description = "NyTelusUser Id")
            @PathVariable("myTelusId") String myTelusId) {
        return userProfileService.findUserProfilesByMyTelusId(myTelusId);
    }

    @Operation(
            tags = {"Primary User Profile"},
            summary = "get a list of primary user profiles related to the specified myTelusId",
            description = "a list of primary user profiles.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list of primary user profiles owned by a my telus user",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = PrimaryUserProfileDto.class,
                                                    type = "object"
                                            )
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{myTelusId}/primary-user-profiles")
    public List<PrimaryUserProfileDto> getPrimaryUserProfilesByMyTelusId(
            @Parameter(in = ParameterIn.PATH, description = "NyTelusUser Id")
            @PathVariable("myTelusId") String myTelusId) {
        return userProfileService.findPrimaryUserProfilesByMyTelusId(myTelusId);
    }

    @Operation(
            tags = {"Sub User Profile", "Primary User Profile"},
            summary = "get a list of sub user profiles owned the specified primary user profile",
            description = "a list of sub user profiles."
    )
    @GetMapping("/primary-user-profiles/{primaryUserProfileId}/sub-user-profiles")
    public List<SubUserProfileDto> getSubUserProfilesOfPrimaryUserProfile(
            @Parameter(in = ParameterIn.PATH, description = "primary user profile id")
            @PathVariable("primaryUserProfileId") String primaryUserProfileId) {
        return userProfileService.getSubUserProfilesByPrimaryUserProfileId(primaryUserProfileId);
    }

    @Operation(
            tags = {"Primary User Profile"},
            summary = "Create a primary user profile with home address",
            description = "create s primary user profile and home address at the same time. the primary user profile will be the owner of all verticals of the same home address"
    )
    @PostMapping("/primary-user-profiles")
    public PrimaryUserProfileDto createPrimaryUserProfile(
            @RequestBody CreatePrimaryUserProfileRequest createPrimaryUserProfileRequest) {
        return userProfileService.createPrimaryUserProfile(createPrimaryUserProfileRequest);
    }

    @Operation(
            tags = {"Primary User Profile"},
            summary = "update home address of the primary user profile",
            description =
                    "home address and primary user profile has a one one relationship, so primaryUserProfileId can be used to identify a home address."
    )
    @PutMapping("/primary-user-profiles/{primaryUserProfileId}/home-address")
    public void updateHomeAddress(
            @RequestBody() PropertyDto homeAddressDto,
            @Parameter(in = ParameterIn.PATH, description = "id of the primary user profile")
            @PathVariable(value = "primaryUserProfileId") String primaryUserProfileId
    ) {
        userProfileService.updateHomeAddress(primaryUserProfileId, homeAddressDto);
    }

    @Operation(
            tags = {"Sub User Profile", "Primary User Profile"},
            summary = "Create a sub user profile",
            description = "the sub user profile will be shared by all verticals."
    )
    @PostMapping("/primary-user-profiles/{primaryUserProfileId}/sub-user-profiles")
    public SubUserProfileDto createSubUserProfile(
            @RequestBody() CreateSubUserProfileRequest createSubUserProfileRequest,
            @Parameter(in = ParameterIn.PATH, description = "id of the primary user profile")
            @PathVariable(value = "primaryUserProfileId") String primaryUserProfileId
    ) {
        return userProfileService.createSubUserProfile(primaryUserProfileId, createSubUserProfileRequest);
    }

    @Operation(
            tags = {"User Profile", "User Profile Link"},
            summary = "Create a user profile link",
            description = "Link from a primary user profile or sub user profile to another primary user profile."
    )
    @PostMapping("/{userProfileId}/user-profile-links")
    public UserProfileLinkDto createUserProfileLink(
            @Parameter(in = ParameterIn.PATH, description = "id of the linked user profile")
            @PathVariable(value = "userProfileId") String linkedUserProfileId,
            @RequestBody() String primaryUserProfileId) {
        return userProfileService.createUserProfileLink(primaryUserProfileId, linkedUserProfileId);
    }

    @Operation(
            tags = {"User Profile", "User Profile Link"},
            summary = "get a list of sub user profiles owned the specified primary user profile",
            description = "a list of sub user profiles."
    )
    @GetMapping("/{userProfileId}/user-profile-links")
    public List<UserProfileLinkDto> findUserProfileLinks(
            @Parameter(in = ParameterIn.PATH, description = "id of the linked user profile")
            @PathVariable(value = "userProfileId") String linkedUserProfileId) {
        return userProfileService.findLinkedUserProfiles(linkedUserProfileId);
    }

    @Operation(
            tags = {"User Profile"},
            summary = "remove a user profile including primary user profile, sub user profile and user profile link"
    )
    @DeleteMapping("/{userProfileId}")
    public void removeUserProfile(
            @Parameter(in = ParameterIn.PATH, description = "id of user profile")
            @PathVariable(value = "userProfileId") String userProfileId) {
        userProfileService.removeUserProfile(userProfileId);
    }

    @Operation(
            tags = {"Sub User Profile"},
            summary = "bind a myTelusId with the sub user profile",
            description = "it can be called when an invitee accepts an invitation."
    )
    @PutMapping("/sub-user-profiles/{subUserProfileId}")
    public void bindMyTelusId(
            @RequestBody() BindMyTelusIdRequest bindMyTelusIdRequest,
            @Parameter(in = ParameterIn.PATH, description = "id of the sub user profile")
            @PathVariable(value = "subUserProfileId") String subUserProfileId
    ) {
        userProfileService.bindMyTelusId(subUserProfileId, bindMyTelusIdRequest.myTelusId());
    }

    @Operation(
            tags = {"User Profile"},
            summary = "update a primary / sub user profile",
            description =
                    "the same endpoint can be used to update the primary user profile and sub user profile."
    )
    @PutMapping("/{userProfileId}")
    public void updateUserProfile(
            @RequestBody() UpdateUserProfileRequest updateUserProfileRequest,
            @Parameter(in = ParameterIn.PATH, description = "can be id of a primary as well as sub user profile")
            @PathVariable(value = "userProfileId") String userProfileId) {
        userProfileService.updateUserProfile(userProfileId, updateUserProfileRequest);
    }
}
