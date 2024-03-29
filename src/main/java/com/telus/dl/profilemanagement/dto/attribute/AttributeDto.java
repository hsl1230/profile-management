package com.telus.dl.profilemanagement.dto.attribute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
//@Accessors(fluent = true)
public class AttributeDto {
    @JsonProperty(value = "name", required = true)
    @NotBlank
    private String name;

    @JsonProperty(value = "value", required = true)
    @NotNull
    private Object value;

    @JsonProperty(value = "isSensitive", required = true)
    private boolean isSensitive;

    @JsonProperty(value = "verticalId")
    private String verticalId;
}
