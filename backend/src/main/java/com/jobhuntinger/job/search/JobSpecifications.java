package com.jobhuntinger.job.search;

import com.jobhuntinger.job.entity.Job;
import com.jobhuntinger.user.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class JobSpecifications {
    public static Specification<Job> hasUser(User user) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user"), user);
    }

    public static Specification<Job> hasJobTitleOrCompanyNameLike(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            String pattern = "%" + keyword.toLowerCase() + "%";

            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("jobTitle")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("companyName")), pattern)
            );
        };
    }

    public static Specification<Job> createdBefore(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> {
            if(date == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.lessThan(root.get("createdAt"), date);
        };
    }
}
