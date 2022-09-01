package com.telus.dl.profilemanagement.document.permission;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class PeriodOfTime {
    private LocalTime from;
    private LocalTime to;
}
