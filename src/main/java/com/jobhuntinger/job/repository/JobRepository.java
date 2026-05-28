package com.jobhuntinger.job.repository;

import com.jobhuntinger.job.entity.Job;
import com.jobhuntinger.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    Page<Job> findByUser(User user, Pageable pageable);

    @Query("""
            SELECT j FROM Job j
            WHERE j.user = :user
              AND (
                    LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%'))
                 OR LOWER(j.user.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))
              )
            """)
    Page<Job> searchJobsByUser(
            @Param("user") User user,
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
