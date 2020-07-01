package com.jpop4.converter;

import com.jpop4.domain.common.User;
import com.jpop4.dto.user.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDto toUserDto(User userEntity);
    User toUser(UserDto userDto);
    List<UserDto> toUserDtos(Iterable<User> userEntities);
}
