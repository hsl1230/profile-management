package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "user-vertical-enablement")
@Getter
@Setter
public class UserVerticalEnablement {
    @Id
    private UserVerticalId id;

    @NotNull
    private String userName;

    @NotNull
    private String roleCode;

    private boolean isEnabled;
}
