package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(fluent = true)
public class VerticalRoleId {
    @NotBlank
    private String verticalId;
    @NotBlank
    private String roleCode;
}
