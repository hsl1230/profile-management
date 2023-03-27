package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(fluent = true)
public class UserVerticalEnablementDto {
    @JsonProperty(value = "verticalId", required = true)
    @NotBlank
    private String verticalId;

    @JsonProperty(value = "userProfileId", required = true)
    @NotBlank
    private String userProfileId;

    @JsonProperty(value = "userName", required = true)
    @NotBlank
    private String userName;

    @JsonProperty(value = "roleCode", required = true)
    @NotBlank
    private String roleCode;

    @JsonProperty(value = "isEnabled", required = true)
    private boolean isEnabled;
}
