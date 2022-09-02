package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrimaryUserProfileDto extends AbstractUserProfileDto {
    @JsonProperty(value = "homeAddress", required = true)
    @Accessors(fluent = true)
    private PropertyDto property;
}
