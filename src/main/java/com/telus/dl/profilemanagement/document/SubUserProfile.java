package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SubUserProfile extends UserProfile {
    @NotNull
    private String primaryUserProfileId;
}
