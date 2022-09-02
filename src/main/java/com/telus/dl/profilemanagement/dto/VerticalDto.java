package com.telus.dl.profilemanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class VerticalDto {
    private String id;
    private String verticalName;
    private String description;
}
