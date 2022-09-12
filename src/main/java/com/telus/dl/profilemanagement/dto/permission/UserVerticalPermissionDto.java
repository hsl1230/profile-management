package com.telus.dl.profilemanagement.dto.permission;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserVerticalPermissionDto extends PermissionDto {
    @NotBlank
    private String id;
    @NotBlank
    private String verticalId;
    @NotBlank
    private String userProfileId;
}
