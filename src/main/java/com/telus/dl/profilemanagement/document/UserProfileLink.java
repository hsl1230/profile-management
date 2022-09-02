package com.telus.dl.profilemanagement.document;

import com.telus.dl.profilemanagement.dto.ProfileStatus;
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
public class UserProfileLink {
    public UserProfileLink() {
        this.userProfileType(UserProfileType.LINK);
    }

    @Id
    private String id;

    @NotNull
    private String primaryUserProfileId;

    @NotNull
    private String linkedUserProfileId;

    @NotNull
    @Setter(AccessLevel.PRIVATE)
    private UserProfileType userProfileType;

    @NotNull
    private ProfileStatus status;

}
