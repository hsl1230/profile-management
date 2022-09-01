package com.telus.dl.profilemanagement.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class UserVerticalId {
    @NotNull
    private String verticalId;
    @NotNull
    private String userProfileId;
}
