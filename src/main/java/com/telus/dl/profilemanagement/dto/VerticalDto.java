package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(fluent = true)
public class VerticalDto {
    @JsonProperty(value = "id", required = true)
    @NotBlank
    private String id;

    @JsonProperty(value = "verticalName", required = true)
    @NotBlank
    private String verticalName;

    @JsonProperty(value = "description")
    private String description;
}
