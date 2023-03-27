package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "user-vertical-enablement")
@Getter
@Setter
public class UserVerticalEnablement {
    @Id
    private UserVerticalId id;

    @NotBlank
    private String userName;

    @NotBlank
    private String roleCode;

    private boolean isEnabled;
}
