package com.telus.dl.profilemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class BindMyTelusIdRequest {
    @JsonProperty(value = "myTelusId", required = true)
    @Accessors(fluent = true)
    private String myTelusId;
}
