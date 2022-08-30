package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
public class HomeAddressDto {
    @JsonProperty(value = "name", required = true)
    @Accessors(fluent = true)
    private String name;

    @JsonProperty(value = "address", required = true)
    @Accessors(fluent = true)
    private String address;

    @JsonProperty(value = "description", required = false)
    @Accessors(fluent = true)
    private String description;
}
