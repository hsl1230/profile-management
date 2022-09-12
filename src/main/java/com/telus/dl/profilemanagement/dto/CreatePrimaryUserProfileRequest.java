package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Accessors(fluent = true)
public class CreatePrimaryUserProfileRequest {
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
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @JsonProperty(value = "property", required = true)
    @NotNull
    private PropertyDto property;

    @JsonProperty(value = "myTelusId")
    @JsonPropertyDescription("set when accept an invitation")
    private String myTelusId;
}
