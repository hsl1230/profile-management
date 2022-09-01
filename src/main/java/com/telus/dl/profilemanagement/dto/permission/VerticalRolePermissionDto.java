package com.telus.dl.profilemanagement.dto.permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerticalRolePermissionDto extends PermissionDto {
    private String id;
    private String verticalId;
    private String roleCode;
}
