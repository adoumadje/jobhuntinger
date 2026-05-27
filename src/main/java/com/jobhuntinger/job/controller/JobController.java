package com.jobhuntinger.job.controller;

import com.jobhuntinger.common.dto.ResponseDto;
import com.jobhuntinger.job.dto.JobDto;
import com.jobhuntinger.job.service.IJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/jobs")
public class JobController {
    private final IJobService jobService;

    @PostMapping
    public ResponseEntity<ResponseDto> registerJobApplication(Authentication authentication, @Validated @RequestBody JobDto jobDto) {
        ResponseDto responseDto = jobService.registerJob(authentication, jobDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
