package com.jobhuntinger.job.entity;

import com.jobhuntinger.common.entity.BaseEntity;
import com.jobhuntinger.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
public class Job extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private UUID jobPublicId;
    private String companyName;
    private String companyLogoUrl;
    private String jobTitle;
    @Lob
    private String jobDescriptionText;
    private String jobDescriptionDocumentUrl;
    private String resumeName;
    private String resumeUrl;
}
