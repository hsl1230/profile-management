package com.telus.dl.profilemanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class UserVerticalEnablementDto {
    private String verticalId;
    private String UserProfileId;

    private String userName;
    private String roleCode;
}
