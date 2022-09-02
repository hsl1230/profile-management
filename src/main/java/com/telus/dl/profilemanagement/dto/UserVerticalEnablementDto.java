package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class UserVerticalEnablementDto {
    @JsonProperty(value = "verticalId", required = true)
    private String verticalId;

    @JsonProperty(value = "UserProfileId", required = true)
    private String UserProfileId;

    @JsonProperty(value = "userName", required = true)
    private String userName;

    @JsonProperty(value = "roleCode", required = true)
    private String roleCode;
}
