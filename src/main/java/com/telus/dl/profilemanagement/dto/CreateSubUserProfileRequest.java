package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
public class CreateSubUserProfileRequest {
    @JsonProperty(value = "firstName", required = true)
    @Accessors(fluent = true)
    private String firstName;

    @JsonProperty(value = "lastName", required = true)
    @Accessors(fluent = true)
    private String lastName;

    @JsonProperty(value = "phoneNumber", required = true)
    @Accessors(fluent = true)
    private String phoneNumber;

    @JsonProperty(value = "email", required = true)
    @Accessors(fluent = true)
    private String email;
}
