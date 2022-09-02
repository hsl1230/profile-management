package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class PropertyDto {
    @JsonProperty(value = "name", required = true)
    @Accessors(fluent = true)
    private String name;

    private String unit;

    @JsonProperty(value = "street", required = true)
    private String street;

    @JsonProperty(value = "city", required = true)
    private String city;

    @JsonProperty(value = "province", required = true)
    private String province;

    @JsonProperty(value = "country", required = true)
    private String country;

    @JsonProperty(value = "postCode", required = true)
    private String postCode;

    @JsonProperty(value = "description", required = false)
    private String description;
}
