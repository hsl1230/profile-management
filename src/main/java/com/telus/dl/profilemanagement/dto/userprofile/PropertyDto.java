package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(fluent = true)
public class PropertyDto {
    @JsonProperty(value = "name", required = true)
    @NotBlank
    private String name;

    @JsonProperty(value = "address", required = true)
    @NotNull
    private AddressDto address;

    @JsonProperty(value = "description", required = false)
    private String description;
}
