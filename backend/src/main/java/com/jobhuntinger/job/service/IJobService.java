package com.jobhuntinger.job.service;

import com.jobhuntinger.common.dto.ResponseDto;
import com.jobhuntinger.job.dto.JobDto;
import com.jobhuntinger.job.dto.JobFilters;
import com.jobhuntinger.job.dto.JobSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface IJobService {
    ResponseDto registerJob(Authentication authentication, JobDto jobDto);

    Page<JobSummaryDto> findJobs(Authentication authentication, JobFilters jobFilters);

    JobDto getJobDetails(UUID jobPublicId);
}
