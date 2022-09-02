package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class SubUserProfileDto extends AbstractUserProfileDto {
        @JsonProperty(value = "primaryUserProfile")
        @Accessors(fluent = true)
        private PrimaryUserProfileDto primaryUserProfile;
}
