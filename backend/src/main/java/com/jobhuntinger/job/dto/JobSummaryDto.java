package com.jobhuntinger.job.dto;

import com.jobhuntinger.job.validation.AtLeastOneJobDescription;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AtLeastOneJobDescription
public class JobSummaryDto {
    private String companyName;
    private String companyLogoUrl;
    private String jobTitle;
    private UUID jobPublicId;
    private String resumeUrl;
    private String resumeName;
    private LocalDateTime dateTime;
    private String dateDisplay;
    private String timeDisplay;
}
