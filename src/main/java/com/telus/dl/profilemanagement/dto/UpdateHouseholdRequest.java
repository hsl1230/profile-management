package com.telus.dl.profilemanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Accessors(fluent = true)
public class UpdateHouseholdRequest {
    @JsonProperty(value = "householdName", required = true)
    private String householdName;

    @JsonProperty(value = "description", required = true)
    private String description;

    @JsonProperty(value = "address", required = true)
    private String address;
}
