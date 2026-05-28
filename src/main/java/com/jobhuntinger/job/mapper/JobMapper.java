package com.jobhuntinger.job.mapper;

import com.jobhuntinger.job.dto.JobDto;
import com.jobhuntinger.job.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface JobMapper {
    Job toJob(JobDto jobDto);

    JobDto toJobDto(Job job);
}
