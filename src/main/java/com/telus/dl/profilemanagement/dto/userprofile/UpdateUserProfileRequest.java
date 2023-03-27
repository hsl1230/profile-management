package com.telus.dl.profilemanagement.dto.userprofile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
public class UpdateUserProfileRequest {
    @JsonProperty(value = "userName")
    @Accessors(fluent = true)
    private String userName;

    @JsonProperty(value = "phoneNumber")
    @Accessors(fluent = true)
    private String phoneNumber;

    @JsonProperty(value = "email")
    @Accessors(fluent = true)
    private String email;

    @JsonProperty(value = "myTelusId")
    @Accessors(fluent = true)
    private String myTelusId;

    @JsonProperty(value = "status")
    @Accessors(fluent = true)
    private ProfileStatus status;
}
