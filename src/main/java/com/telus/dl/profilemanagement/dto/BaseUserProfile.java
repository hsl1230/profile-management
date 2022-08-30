package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
public abstract class BaseUserProfile {
    @JsonProperty(value = "id", required = true)
    @Accessors(fluent = true)
    private String id;

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

    @JsonProperty(value = "status", required = true)
    @Accessors(fluent = true)
    private ProfileStatus status;

    @JsonProperty(value = "myTelusId")
    @JsonPropertyDescription("set when accept an invitation")
    @Accessors(fluent = true)
    private String myTelusId;
}