package com.jobhuntinger.user.entity;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String profilePictureUrl;
}
