package com.jobhuntinger.user.controller;

import com.jobhuntinger.user.dto.UserDto;
import com.jobhuntinger.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getAuthenticatedUserUser(Authentication authentication) {
        UserDto userDto = userService.getOrCreateUser(authentication);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
