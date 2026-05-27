package com.jobhuntinger.job.repository;

import com.jobhuntinger.job.entity.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {
}
