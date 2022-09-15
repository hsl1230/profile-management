package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(fluent = true)
public class PrimaryUserProfile extends NonLinkUserProfile {
    public PrimaryUserProfile() {
        this.userProfileType(UserProfileType.PRIMARY);
    }

    @Getter
    @Setter
    public static class Property {
        @NotNull
        private String name;
        @NotNull
        private Address address;
        private String description;
    }

    @Getter
    @Setter
    public static class Address {
        private String unit;
        @NotNull
        private String street;
        @NotNull
        private String city;
        @NotNull
        private String province;
        @NotNull
        private String country;
        @NotNull
        private String postCode;
    }

    @NotNull
    private PrimaryUserProfile.Property property;
}
