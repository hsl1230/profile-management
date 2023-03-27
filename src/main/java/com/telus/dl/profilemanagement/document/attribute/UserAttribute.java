package com.telus.dl.profilemanagement.document.attribute;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "user-attribute")
@Getter
@Setter
@Accessors(fluent = true)
public class UserAttribute {
    @Id
    private UserAttributeId id;

    @NotNull
    private Object value;

    private boolean isSensitive;
}
