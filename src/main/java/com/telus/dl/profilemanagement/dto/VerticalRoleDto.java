package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class VerticalRoleDto {
    @JsonProperty(value = "verticalId", required = true)
    private String verticalId;

    @JsonProperty(value = "roleCode", required = true)
    private String roleCode;

    @JsonProperty(value = "roleName", required = true)
    private String roleName;

    @JsonProperty(value = "description")
    private String description;
}