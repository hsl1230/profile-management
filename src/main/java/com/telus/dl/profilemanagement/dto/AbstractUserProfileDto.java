package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.telus.dl.profilemanagement.document.UserProfileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter()
@Accessors(fluent = true)
public abstract class AbstractUserProfileDto {
    @JsonProperty(value = "id", required = true)
    private String id;

    @JsonProperty(value = "userProfileType", required = true)
    @Setter(AccessLevel.PROTECTED)
    private UserProfileType userProfileType;

    @JsonProperty(value = "firstName", required = true)
    private String firstName;

    @JsonProperty(value = "middleName", required = true)
    private String middleName;

    @JsonProperty(value = "lastName", required = true)
    private String lastName;

    @JsonProperty(value = "phoneNumber", required = true)
    private String phoneNumber;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "status", required = true)
    private ProfileStatus status;

    @JsonProperty(value = "myTelusId")
    @JsonPropertyDescription("set when accept an invitation")
    private String myTelusId;
}