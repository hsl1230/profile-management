package com.telus.dl.profilemanagement.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@Accessors(fluent = true)
@AllArgsConstructor
public class VerticalRoleId {
    @NotBlank
    private String verticalId;
    @NotBlank
    private String roleCode;
}
