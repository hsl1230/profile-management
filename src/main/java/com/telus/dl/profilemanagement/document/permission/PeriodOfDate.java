package com.telus.dl.profilemanagement.document.permission;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
public class PeriodOfDate {
    private List<DayOfWeek> weekDays;
    private List<Integer> monthDays;
}
