package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "household")
@Getter
@Setter
@Accessors(fluent = true)
public class Household {
    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String householdName;

    private String description;

    private String address;

    @NotBlank
    private String customerId;
}
