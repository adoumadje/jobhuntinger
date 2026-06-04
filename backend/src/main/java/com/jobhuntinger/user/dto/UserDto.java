package com.jobhuntinger.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String profilePictureUrl;
}
