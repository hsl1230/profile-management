package com.telus.dl.profilemanagement.document;

import com.telus.dl.profilemanagement.dto.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "user_profile")
@Getter
@Setter
public class LinkedUserProfile {
    @Id
    private String id;

    @NotNull
    private String parentUserProfileId;

    @NotNull
    private String ownerProfileId;

    @NotNull
    private UserProfileType userProfileType;

    @NotNull
    private ProfileStatus status;

}
