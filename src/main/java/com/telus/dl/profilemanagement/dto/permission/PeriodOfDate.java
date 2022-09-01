package com.telus.dl.profilemanagement.dto.permission;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
public class PeriodOfDate {
    @NotNull
    private List<DayOfWeek> weekDays;

    @ArraySchema(schema = @Schema(description = "day of the month (1 - 31)", example = "1", required = true, nullable = false))
    private List<Integer> monthDays;
}
