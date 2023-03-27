package com.telus.dl.profilemanagement.dto.userprofile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.telus.dl.profilemanagement.document.userprofile.UserProfileType;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Accessors(fluent = true)
public class CreateUserProfileRequest {
    @JsonProperty(value = "userProfileType", required = true)
    @NotNull
    private UserProfileType userProfileType;

    @JsonProperty(value = "userName", required = true)
    @NotBlank
    private String userName;

    @JsonProperty(value = "phoneNumber", required = true)
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$")
    private String phoneNumber;

    @JsonProperty(value = "email", required = true)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @JsonProperty(value = "householdId", required = true)
    @NotBlank
    private String householdId;

    @JsonProperty(value = "myTelusId")
    @JsonPropertyDescription("set when accept an invitation")
    private String myTelusId;

    @JsonProperty(value = "linkedUserProfileId")
    @JsonPropertyDescription("it is mandatory when the user profile is of type LINK")
    private String linkedUserProfileId;
}
