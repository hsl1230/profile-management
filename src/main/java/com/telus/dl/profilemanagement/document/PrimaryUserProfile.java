package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PrimaryUserProfile extends UserProfile {
    public PrimaryUserProfile() {
        this.setUserProfileType(UserProfileType.PRIMARY);
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
