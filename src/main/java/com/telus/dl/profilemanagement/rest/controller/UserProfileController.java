package com.telus.dl.profilemanagement.rest.controller;

import com.telus.dl.profilemanagement.document.userprofile.UserProfileType;
import com.telus.dl.profilemanagement.dto.userprofile.CreateUserProfileRequest;
import com.telus.dl.profilemanagement.dto.userprofile.UpdateUserProfileRequest;
import com.telus.dl.profilemanagement.dto.userprofile.UserProfileDto;
import com.telus.dl.profilemanagement.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/profile-management/user-profiles")
@Validated
public class UserProfileController {

  private final UserProfileService userProfileService;

  @Autowired
  public UserProfileController(UserProfileService userProfileService) {
    this.userProfileService = userProfileService;
  }

  @Operation(
    tags = { "User Profile" },
    summary = "get a list of user profiles of types specified profileTypes in related to the specified customerId, linkedUserProfile or householdId",
    description = "a list mixing of primary user profiles, sub user profiles and linked user profiles",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "a list mixing of primary user profiles, sub user profiles and linked user profiles",
        content = @Content(
          mediaType = "application/json",
          array = @ArraySchema(
            schema = @Schema(anyOf = { UserProfileDto.class }, type = "object")
          )
        )
      ),
    }
  )
  @GetMapping
  public List<UserProfileDto> findUserProfiles(
    @Parameter(
      name = "customerId",
      in = ParameterIn.QUERY,
      description = "Customer Id",
      required = false
    ) @RequestParam(value = "customerId", required = false) String customerId,
    @Parameter(
      name = "linkedUserProfileId",
      in = ParameterIn.QUERY,
      description = "linked user profile id",
      required = false
    ) @RequestParam(
      value = "linkedUserProfileId",
      required = false
    ) String linkedUserProfileId,
    @Parameter(
      name = "householdId",
      in = ParameterIn.QUERY,
      description = "household id",
      required = false
    ) @RequestParam(value = "householdId", required = false) String householdId,
    @Parameter(
      name = "profileTypes",
      in = ParameterIn.QUERY,
      description = "user profile type",
      required = false
    ) @RequestParam(
      value = "profileTypes",
      required = false
    ) List<UserProfileType> userProfileTypes
  ) {
    return userProfileService.findUserProfiles(
      customerId,
      linkedUserProfileId,
      userProfileTypes,
      householdId
    );
  }

  @Operation(
    tags = { "User Profile" },
    summary = "Create an user profile of any type",
    description = "create an user profile of specified type"
  )
  @PostMapping("")
  public UserProfileDto createUserProfile(
    @Valid @RequestBody CreateUserProfileRequest createUserProfileRequest
  ) {
    return userProfileService.createUserProfile(createUserProfileRequest);
  }

  @Operation(
    tags = { "User Profile" },
    summary = "find a user profile including primary user profile, sub user profile and user profile link by id"
  )
  @GetMapping("/{userProfileId}")
  public UserProfileDto findUserProfile(
    @Parameter(
      in = ParameterIn.PATH,
      description = "id of user profile"
    ) @PathVariable(value = "userProfileId") String userProfileId
  ) {
    return userProfileService.findUserProfileById(userProfileId);
  }

  @Operation(
    tags = { "User Profile" },
    summary = "remove a user profile including primary user profile, sub user profile and user profile link"
  )
  @DeleteMapping("/{userProfileId}")
  public void removeUserProfile(
    @Parameter(
      in = ParameterIn.PATH,
      description = "id of user profile"
    ) @PathVariable(value = "userProfileId") String userProfileId
  ) {
    userProfileService.removeUserProfile(userProfileId);
  }

  @Operation(
    tags = { "User Profile" },
    summary = "update a primary / sub user profile",
    description = "the same endpoint can be used to update the primary user profile and sub user profile."
  )
  @PutMapping("/{userProfileId}")
  public void updateUserProfile(
    @Valid @RequestBody UpdateUserProfileRequest updateUserProfileRequest,
    @Parameter(
      in = ParameterIn.PATH,
      description = "can be id of a primary as well as sub user profile"
    ) @PathVariable(value = "userProfileId") String userProfileId
  ) {
    userProfileService.updateUserProfile(
      userProfileId,
      updateUserProfileRequest
    );
  }
}
