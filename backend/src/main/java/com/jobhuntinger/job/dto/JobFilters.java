package com.jobhuntinger.job.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class JobFilters {
    private String keyword;
    @NotNull(message = "page number is mandatory")
    @Min(value = 0, message = "page number is at least 0")
    private Integer pageNumber;
    @NotNull(message = "rows is mandatory")
    @Min(value = 5, message = "number of rows must be between 5 and 10")
    @Max(value = 10, message = "number of rows must be between 5 and 10")
    private Integer rows;
    private LocalDate toDate;
}
