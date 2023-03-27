package com.telus.dl.profilemanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(fluent = true)
public class HouseholdDto {
    @JsonProperty(value = "id", required = true)
    @NotBlank
    private String id;

    @JsonProperty(value = "householdName", required = true)
    @NotBlank
    private String householdName;

    private String description;

    private String address;

    @JsonProperty(value = "customerId", required = true)
    @NotBlank
    private String customerId;
}
