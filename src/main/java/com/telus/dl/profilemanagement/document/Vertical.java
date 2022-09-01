package com.telus.dl.profilemanagement.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "vertical")
@Getter
@Setter
public class Vertical {
    @Id
    private String id;

    @NotNull
    private String verticalName;

    private String description;
}
