package com.telus.dl.profilemanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(fluent = true)
public class CreateHouseholdRequest {
    @JsonProperty(value = "householdName", required = true)
    @NotBlank
    private String householdName;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "address")
    private String address;
}
