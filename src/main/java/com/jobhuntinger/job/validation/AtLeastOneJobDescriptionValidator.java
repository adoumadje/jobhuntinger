package com.jobhuntinger.job.validation;

import com.jobhuntinger.job.dto.JobDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneJobDescriptionValidator implements ConstraintValidator<AtLeastOneJobDescription, JobDto> {
    @Override
    public boolean isValid(JobDto jobDto, ConstraintValidatorContext constraintValidatorContext) {
        return (jobDto.getJobDescriptionText() != null && !jobDto.getJobDescriptionText().isEmpty())
                || (jobDto.getJobDescriptionDocumentUrl() != null && !jobDto.getJobDescriptionDocumentUrl().isEmpty());
    }
}
