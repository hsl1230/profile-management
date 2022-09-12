package com.telus.dl.profilemanagement.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserVerticalId {
    @NotNull
    private String verticalId;
    @NotNull
    private String userProfileId;
}
