package com.telus.dl.profilemanagement.document.userprofile;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(fluent = true)
public class UserProfileLink extends UserProfile {
    public UserProfileLink() {
        this.userProfileType(UserProfileType.LINK);
    }

    @NotNull
    private String primaryUserProfileId;

    @NotNull
    private String linkedUserProfileId;
}
