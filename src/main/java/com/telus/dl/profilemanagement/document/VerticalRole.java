package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "vertical")
@Getter
@Setter
public class VerticalRole {
    @Id
    private VerticalRoleId id;

    @NotNull
    private String roleName;

    private String description;

}
