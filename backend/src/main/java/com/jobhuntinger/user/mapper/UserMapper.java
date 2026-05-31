package com.jobhuntinger.user.mapper;

import com.jobhuntinger.user.dto.UserDto;
import com.jobhuntinger.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toUserDto(User user);
}
