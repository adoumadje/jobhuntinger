package com.jobhuntinger.job.service;

import com.jobhuntinger.common.dto.ResponseDto;
import com.jobhuntinger.job.dto.JobDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface IJobService {
    ResponseDto registerJob(Authentication authentication, JobDto jobDto);

    Page<JobDto> findJobs(Authentication authentication, String keyword, Integer pageNumber, int pageSize);
}
