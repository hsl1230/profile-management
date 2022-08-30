package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
public class UpdateUserProfileRequest {
    @JsonProperty(value = "firstName")
    @Accessors(fluent = true)
    private String firstName;

    @JsonProperty(value = "lastName")
    @Accessors(fluent = true)
    private String lastName;

    @JsonProperty(value = "phoneNumber")
    @Accessors(fluent = true)
    private String phoneNumber;

    @JsonProperty(value = "email")
    @Accessors(fluent = true)
    private String email;
}
