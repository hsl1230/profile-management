package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrimaryUserProfileDto extends AbstractUserProfileDto {
    @JsonProperty(value = "property", required = true)
    @Accessors(fluent = true)
    @NotNull
    private PropertyDto property;
}
