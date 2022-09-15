package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(fluent = true)
public abstract class NonLinkUserProfile extends UserProfile {

    @NotNull
    private String firstName;

    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

    private String myTelusId;
}
