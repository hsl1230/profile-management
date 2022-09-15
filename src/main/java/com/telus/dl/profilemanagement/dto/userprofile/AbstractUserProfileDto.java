package com.telus.dl.profilemanagement.dto.userprofile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.telus.dl.profilemanagement.document.userprofile.UserProfileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter()
@Accessors(fluent = true)
public abstract class AbstractUserProfileDto {
    @JsonProperty(value = "id", required = true)
    @NotBlank
    private String id;

    @JsonProperty(value = "userProfileType", required = true)
    @Setter(AccessLevel.PROTECTED)
    @NotBlank
    private UserProfileType userProfileType;

    @JsonProperty(value = "firstName", required = true)
    @NotBlank
    private String firstName;

    @JsonProperty(value = "middleName")
    private String middleName;

    @JsonProperty(value = "lastName", required = true)
    @NotBlank
    private String lastName;

    @JsonProperty(value = "phoneNumber", required = true)
    @NotBlank
    private String phoneNumber;

    @JsonProperty(value = "email", required = true)
    @NotBlank
    private String email;

    @JsonProperty(value = "status", required = true)
    @NotBlank
    private ProfileStatus status;

    @JsonProperty(value = "myTelusId")
    @JsonPropertyDescription("set when accept an invitation")
    private String myTelusId;
}