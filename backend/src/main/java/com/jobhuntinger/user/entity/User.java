package com.jobhuntinger.user.entity;

import com.jobhuntinger.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_entity")
public class User extends BaseEntity {
    private String firstname;
    private String lastname;
    private String email;
    private String profilePictureUrl;
}
