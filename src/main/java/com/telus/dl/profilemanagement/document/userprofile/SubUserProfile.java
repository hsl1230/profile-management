package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(fluent = true)
public class SubUserProfile extends NonLinkUserProfile {
    public SubUserProfile() {
        this.userProfileType(UserProfileType.SUB);
    }

    @NotNull
    private String primaryUserProfileId;
}
