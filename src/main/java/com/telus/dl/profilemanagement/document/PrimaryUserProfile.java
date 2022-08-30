package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrimaryUserProfile extends UserProfile {
    @Getter
    @Setter
    public static class HomeAddress {
        private String name;
        private String address;
        private String description;
    }
    private HomeAddress homeAddress;
}
