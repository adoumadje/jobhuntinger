package com.jobhuntinger.user.service;

import com.jobhuntinger.user.dto.UserDto;
import com.jobhuntinger.user.entity.User;
import org.springframework.security.core.Authentication;

public interface IUserService {
    UserDto getOrCreateUser(Authentication authentication);

    User getAuthenticatedUser(Authentication authentication);
}
