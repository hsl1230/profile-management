package com.telus.dl.profilemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVerticalRoleRequest {
    private String roleCode;

    private String roleName;
    private String description;
}
