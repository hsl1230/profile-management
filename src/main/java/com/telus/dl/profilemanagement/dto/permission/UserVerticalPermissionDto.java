package com.telus.dl.profilemanagement.dto.permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVerticalPermissionDto extends PermissionDto {
    private String id;
    private String verticalId;
    private String userProfileId;
}
