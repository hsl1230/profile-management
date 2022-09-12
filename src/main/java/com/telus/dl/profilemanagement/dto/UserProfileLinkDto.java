package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telus.dl.profilemanagement.document.UserProfileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(fluent = true)
public class UserProfileLinkDto {
    @JsonProperty(value = "id", required = true)
    @NotBlank
    private String id;

    @JsonProperty(value = "userProfileType", required = true)
    @Setter(AccessLevel.PROTECTED)
    @NotBlank
    private UserProfileType userProfileType;

    @JsonProperty(value = "primaryUserProfile")
    private PrimaryUserProfileDto primaryUserProfile;

    @JsonProperty(value = "linkedUserProfileId")
    private String linkedUserProfileId;

    @JsonProperty(value = "status", required = true)
    @NotNull
    private ProfileStatus status;

}