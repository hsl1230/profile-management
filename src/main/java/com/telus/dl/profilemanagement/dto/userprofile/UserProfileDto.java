package com.telus.dl.profilemanagement.dto.userprofile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.telus.dl.profilemanagement.document.userprofile.UserProfileType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter()
@Accessors(fluent = true)
public class UserProfileDto {
    @JsonProperty(value = "id", required = true)
    @NotBlank
    private String id;

    @JsonProperty(value = "userProfileType", required = true)
    @NotBlank
    private UserProfileType userProfileType;

    @JsonProperty(value = "userName", required = true)
    @NotBlank
    private String userName;

    @JsonProperty(value = "phoneNumber", required = true)
    private String phoneNumber;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "householdId", required = true)
    @NotBlank
    private String householdId;

    @JsonProperty(value = "status", required = true)
    @NotBlank
    private ProfileStatus status;

    @JsonProperty(value = "myTelusId")
    @JsonPropertyDescription("set when accept an invitation")
    private String myTelusId;

    @JsonProperty(value = "linkedUserProfileId")
    @JsonPropertyDescription("it is mandatory when the user profile is of type LINK")
    private String linkedUserProfileId;

}