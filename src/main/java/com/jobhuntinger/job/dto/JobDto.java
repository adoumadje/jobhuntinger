package com.jobhuntinger.job.dto;

import com.jobhuntinger.job.validation.AtLeastOneJobDescription;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AtLeastOneJobDescription
public class JobDto {
    @NotEmpty(message = "companyName is mandatory")
    private String companyName;
    @NotEmpty(message = "companyLogoUrl is mandatory")
    private String companyLogoUrl;
    @NotEmpty(message = "companyLogoUrl is mandatory")
    private String jobTitle;
    private String jobDescriptionText;
    private String jobDescriptionDocumentUrl;
    @NotEmpty(message = "resumeUrl is mandatory")
    private String resumeUrl;
    @NotEmpty(message = "resumeName is mandatory")
    private String resumeName;
    private LocalDateTime dateTime;
    private String dateDisplay;
    private String timeDisplay;
}
