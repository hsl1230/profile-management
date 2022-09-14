package com.telus.dl.profilemanagement.dto.permission;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class PeriodOfTime {
    @Schema(description = "from time", example = "09:00", format = "HH:mm", required = true, nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime from;

    @Schema(description = "to time", example = "17:00", format = "HH:mm", required = true, nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime to;
}
