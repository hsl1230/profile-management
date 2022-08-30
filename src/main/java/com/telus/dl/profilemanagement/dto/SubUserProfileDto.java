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
@EqualsAndHashCode
public class SubUserProfileDto extends BaseUserProfile {
        @JsonProperty(value = "primaryUserProfile", required = true)
        @Accessors(fluent = true)
        private PrimaryUserProfileDto primaryUserProfile;
}
