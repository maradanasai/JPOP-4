package com.jpop4.api.user;

import com.jpop4.dto.user.UserDto;

import java.math.BigInteger;
import java.util.List;

public interface UserApi {
    List<UserDto> findAllUserDetails();

    UserDto findUserDetails(BigInteger userId);

    boolean addUserDetails(UserDto userDto);

    boolean removeUserDetails(BigInteger id);

    UserDto updateUserDetails(UserDto userDto);
}
