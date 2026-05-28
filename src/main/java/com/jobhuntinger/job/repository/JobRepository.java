package com.jobhuntinger.job.repository;

import com.jobhuntinger.job.entity.Job;
import com.jobhuntinger.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {
    Page<Job> findByUser(User user, Pageable pageable);
}
