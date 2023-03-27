package com.telus.dl.profilemanagement.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserVerticalId {
    @NotBlank
    private String verticalId;
    @NotBlank
    private String userProfileId;
}
