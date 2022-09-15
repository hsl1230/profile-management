package com.telus.dl.profilemanagement.document.attribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserAttributeId {
    private String userProfileId;
    private String name;
}
