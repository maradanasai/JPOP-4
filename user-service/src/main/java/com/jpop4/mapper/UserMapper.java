package com.jpop4.mapper;

import com.jpop4.domain.UserDto;
import com.jpop4.repository.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDto toUserDto(User userEntity);
    User toUser(UserDto userDto);
    List<UserDto> toUserDtos(Iterable<User> userEntities);
}
