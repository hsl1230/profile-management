package com.telus.dl.profilemanagement.document.permission;

import com.telus.dl.profilemanagement.document.VerticalRoleId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "vertical-role-permission")
@Getter
@Setter
@Accessors(fluent = true)
public class VerticalRolePermission {
    @Id
    private String id;

    @NotNull(message = "verticalRoleId is needed")
    private VerticalRoleId verticalRoleId;

    @NotNull(message = "resource is needed")
    private Resource resource;

    private PeriodOfTime periodOfTime;
    private PeriodOfDate periodOfDate;
    private EffectiveDates effectiveDates;
}
