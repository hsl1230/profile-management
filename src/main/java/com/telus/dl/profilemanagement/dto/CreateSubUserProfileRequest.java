package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateSubUserProfileRequest {
    @JsonProperty(value = "firstName", required = true)
    @Accessors(fluent = true)
    @NotBlank
    private String firstName;

    @JsonProperty(value = "middleName")
    @Accessors(fluent = true)
    private String middleName;

    @JsonProperty(value = "lastName", required = true)
    @Accessors(fluent = true)
    @NotBlank
    private String lastName;

    @JsonProperty(value = "phoneNumber", required = true)
    @Accessors(fluent = true)
    @NotBlank
    private String phoneNumber;

    @JsonProperty(value = "email", required = true)
    @Accessors(fluent = true)
    @NotBlank
    private String email;
}
