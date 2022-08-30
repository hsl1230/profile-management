package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubUserProfile extends UserProfile {
    private String primaryUserProfileId;
}
