package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class CreatePrimaryUserProfileRequest {
    @JsonProperty(value = "firstName", required = true)
    private String firstName;

    @JsonProperty(value = "middleName")
    private String middleName;

    @JsonProperty(value = "lastName", required = true)
    private String lastName;

    @JsonProperty(value = "phoneNumber", required = true)
    private String phoneNumber;

    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonProperty(value = "property", required = true)
    private PropertyDto property;

    @JsonProperty(value = "myTelusId")
    @JsonPropertyDescription("set when accept an invitation")
    private String myTelusId;
}
