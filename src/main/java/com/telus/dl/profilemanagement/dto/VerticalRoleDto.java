package com.telus.dl.profilemanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class VerticalRoleDto {
    private String verticalId;
    private String roleCode;

    private String roleName;
    private String description;
}
