package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(fluent = true)
public class CreateUserVerticalEnablementRequest {
    @JsonProperty(value = "roleCode", required = true)
    @NotBlank
    private String roleCode;

    @JsonProperty(value = "userName", required = true)
    @NotBlank
    private String userName;
}
