package com.telus.dl.profilemanagement.dto.permission;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PermissionDto {
    private String featureId;

    @NotNull(message = "resource is needed")
    private Resource resource;

    private PeriodOfTime periodOfTime;
    private PeriodOfDate periodOfDate;
    private EffectiveDates effectiveDates;
}
