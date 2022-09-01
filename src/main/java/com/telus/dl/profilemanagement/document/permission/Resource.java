package com.telus.dl.profilemanagement.document.permission;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Resource {
    @NotNull(message = "resource id is needed")
    private String resourceId;

    private String resourceName;

    @NotNull(message = "resource type is needed")
    private ResourceType resourceType;
}
