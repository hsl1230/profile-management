package com.telus.dl.profilemanagement.document.userprofile;

import com.telus.dl.profilemanagement.dto.userprofile.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(collection = "user_profile")
@Getter
@Setter
@Accessors(fluent = true)
public class UserProfile {
    @Id
    @NotBlank
    private String id;

    @NotNull
    private UserProfileType userProfileType;

    @NotBlank
    private String userName;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private String email;

    @NotBlank
    private String householdId;

    @NotNull
    private ProfileStatus status;

    private String myTelusId;

    private String linkedUserProfileId;
}
