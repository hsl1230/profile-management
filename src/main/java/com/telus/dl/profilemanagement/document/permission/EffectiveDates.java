package com.telus.dl.profilemanagement.document.permission;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EffectiveDates {
    private LocalDate from;
    private LocalDate to;
}
