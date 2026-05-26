package com.jobhuntinger.docs.entity;

import com.jobhuntinger.common.entity.BaseEntity;
import com.jobhuntinger.docs.enums.DocumentType;
import com.jobhuntinger.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Document extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String documentName;
    private DocumentType documentType;
    private String DocumentUrl;
    private String googleDriveId;
}
