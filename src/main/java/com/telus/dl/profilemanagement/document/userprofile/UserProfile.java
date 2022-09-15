package com.telus.dl.profilemanagement.document.userprofile;

import com.telus.dl.profilemanagement.dto.userprofile.ProfileStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "user_profile")
@Getter
@Setter
@Accessors(fluent = true)
public abstract class UserProfile {
    @Id
    private String id;

    @NotNull
    @Setter(AccessLevel.PROTECTED)
    private UserProfileType userProfileType;

    @NotNull
    private ProfileStatus status;
}
