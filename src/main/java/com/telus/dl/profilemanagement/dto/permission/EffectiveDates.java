package com.telus.dl.profilemanagement.dto.permission;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EffectiveDates {
    @Schema(description = "from date", example = "2022-01-02", format = "yyyy-MM-dd", required = true, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate from;

    @Schema(description = "to date", example = "2022-10-30", format = "yyyy-MM-dd", required = true, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate to;
}
