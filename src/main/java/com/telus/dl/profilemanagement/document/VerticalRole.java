package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "vertical")
@Getter
@Setter
public class VerticalRole {
    @Id
    private VerticalRoleId id;

    @NotBlank
    private String roleName;

    private String description;

}
