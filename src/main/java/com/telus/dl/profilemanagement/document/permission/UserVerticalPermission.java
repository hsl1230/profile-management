package com.telus.dl.profilemanagement.document.permission;

import com.telus.dl.profilemanagement.document.UserVerticalId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "user-vertical-permission")
@Getter
@Setter
public class UserVerticalPermission {
    @Id
    private String id;

    @NotNull(message = "userProfileVerticalId is needed")
    private UserVerticalId userVerticalId;

    @NotNull(message = "resource is needed")
    private Resource resource;

    private PeriodOfTime periodOfTime;
    private PeriodOfDate periodOfDate;
    private EffectiveDates effectiveDates;

    public UserVerticalPermission userVerticalId(UserVerticalId userVerticalId) {
        this.userVerticalId = userVerticalId;
        return this;
    }
}
