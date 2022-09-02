package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telus.dl.profilemanagement.document.UserProfileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class UserProfileLinkDto {
    @JsonProperty(value = "id", required = true)
    private String id;

    @JsonProperty(value = "status", required = true)
    @Setter(AccessLevel.PROTECTED)
    private UserProfileType userProfileType;

    @JsonProperty(value = "primaryUserProfile")
    private PrimaryUserProfileDto primaryUserProfile;

    @JsonProperty(value = "linkedUserProfile")
    private SubUserProfileDto linkedUserProfile;

    @JsonProperty(value = "status", required = true)
    private ProfileStatus status;

}