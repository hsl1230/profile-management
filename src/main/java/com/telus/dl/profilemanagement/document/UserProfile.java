package com.telus.dl.profilemanagement.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.telus.dl.profilemanagement.dto.ProfileStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "user_profile")
@Getter
@Setter
public class UserProfile {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private ProfileStatus status;

    private String myTelusId;
}
