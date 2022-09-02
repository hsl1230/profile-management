package com.telus.dl.profilemanagement.document;

import com.telus.dl.profilemanagement.dto.ProfileStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "user_profile")
@Getter
@Setter
public abstract class UserProfile {
    @Id
    private String id;

    @NotNull
    private String firstName;

    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

    @NotNull
    @Setter(AccessLevel.PROTECTED)
    private UserProfileType userProfileType;

    @NotNull
    private ProfileStatus status;

    private String myTelusId;
}
