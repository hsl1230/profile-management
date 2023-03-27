package com.telus.dl.profilemanagement.dto.attribute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
//@Accessors(fluent = true)
public class UserAttributeDto {
    @JsonProperty(value = "userProfileId", required = true)
    @NotBlank
    private String userProfileId;

    @JsonProperty(value = "attribute", required = true)
    @NotNull
    private AttributeDto attribute;
}
