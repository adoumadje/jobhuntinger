package com.jobhuntinger.user.service.implementation;

import com.jobhuntinger.user.dto.UserDto;
import com.jobhuntinger.user.entity.User;
import com.jobhuntinger.user.mapper.UserMapper;
import com.jobhuntinger.user.repository.UserRepository;
import com.jobhuntinger.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IUserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getOrCreateUser(Authentication authentication) {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        Jwt jwt = jwtAuthenticationToken.getToken();
        String email = jwt.getClaims().get("email").toString();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            return createUser(jwt);
        }
        User user = optionalUser.get();
        UserDto userDto = userMapper.toUserDto(user);
        userDto.setFullname(user.getFirstname() + " " + user.getLastname());
        return userDto;
    }

    @Override
    public User getAuthenticatedUser(Authentication authentication) {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        Jwt jwt = jwtAuthenticationToken.getToken();
        String email = jwt.getClaims().get("email").toString();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("Unknown user");
        }
        return optionalUser.get();
    }

    private UserDto createUser(Jwt jwt) {
        Map<String, Object> claims = jwt.getClaims();
        String firstname = claims.get("given_name").toString();
        String lastname = claims.get("family_name").toString();
        String email = claims.get("email").toString();
        String profilePictureUrl = claims.get("picture").toString();
        User user = User.builder().firstname(firstname).lastname(lastname)
                .email(email).profilePictureUrl(profilePictureUrl).build();
        User savedUser = userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(user);
        userDto.setFullname(savedUser.getFirstname() + " " + savedUser.getLastname());
        return userDto;
    }
}
