package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PrimaryUserProfile extends UserProfile {
    @Getter
    @Setter
    public static class HomeAddress {
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
    private HomeAddress homeAddress;
}
