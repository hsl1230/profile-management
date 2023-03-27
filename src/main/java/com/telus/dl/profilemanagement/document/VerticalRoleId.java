package com.telus.dl.profilemanagement.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class VerticalRoleId {
    @NotBlank
    private String verticalId;
    @NotBlank
    private String roleCode;
}
