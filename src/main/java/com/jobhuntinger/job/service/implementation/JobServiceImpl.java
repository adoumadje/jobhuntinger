package com.jobhuntinger.job.service.implementation;

import com.jobhuntinger.common.constants.Constants;
import com.jobhuntinger.common.dto.ResponseDto;
import com.jobhuntinger.common.service.DateTimeService;
import com.jobhuntinger.job.dto.JobDto;
import com.jobhuntinger.job.entity.Job;
import com.jobhuntinger.job.mapper.JobMapper;
import com.jobhuntinger.job.repository.JobRepository;
import com.jobhuntinger.job.service.IJobService;
import com.jobhuntinger.user.entity.User;
import com.jobhuntinger.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements IJobService {
    private final JobMapper jobMapper;
    private final JobRepository jobRepository;
    private final IUserService userService;
    private final DateTimeService dateTimeService;

    @Override
    public ResponseDto registerJob(Authentication authentication, JobDto jobDto) {
        User user = userService.getAuthenticatedUser(authentication);
        Job job = jobMapper.toJob(jobDto);
        job.setJobPublicId(UUID.randomUUID());
        job.setUser(user);
        jobRepository.save(job);
        return new ResponseDto(Constants.STATUS_CREATED, Constants.JOB_CREATED_MSG);
    }

    @Override
    public Page<JobDto> findJobs(Authentication authentication, String keyword, Integer pageNumber, int pageSize) {
        if(keyword == null) {
            return findJobs(authentication, pageNumber, pageSize);
        } else {
            return searchJobs(authentication, keyword, pageNumber, pageSize);
        }
    }

    private Page<JobDto> findJobs(Authentication authentication, Integer pageNumber, int pageSize) {
        User user = userService.getAuthenticatedUser(authentication);
        pageNumber = pageNumber == null ? 0 : pageNumber-1;
        Page<Job> jobPage = jobRepository.findByUser(user, PageRequest.of(pageNumber, pageSize));
        return makeJobDtoPage(jobPage);
    }

    private Page<JobDto> searchJobs(Authentication authentication, String keyword, Integer pageNumber, int pageSize) {
        User user = userService.getAuthenticatedUser(authentication);
        pageNumber = pageNumber == null ? 0 : pageNumber-1;
        return null;
    }

    private Page<JobDto> makeJobDtoPage(Page<Job> jobPage) {
        Page<JobDto> jobDtoPage = jobPage.map(job -> {
            JobDto jobDto = jobMapper.toJobDto(job);
            jobDto.setDateTime(job.getCreatedAt());
            jobDto.setDateDisplay(dateTimeService.createDateDisplay(job.getCreatedAt()));
            jobDto.setTimeDisplay(dateTimeService.createTimeDisplay(job.getCreatedAt()));
            return jobDto;
        });
        return jobDtoPage;
    }
}
