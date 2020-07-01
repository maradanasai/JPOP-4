package com.jpop4.service;

import com.jpop4.api.user.UserApi;
import com.jpop4.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserScreenService {

    @Autowired
    private UserApi userApi;

    public UserDto getUserDetails(BigInteger userId) {
        return userApi.findUserDetails(userId);
    }

    public List<UserDto> getAllUserDetails() {
        return userApi.findAllUserDetails();
    }

    public boolean addUserDetails(UserDto userDto) {
        return userApi.addUserDetails(userDto);
    }

    public boolean deleteUserDetails(BigInteger userId) {
        return userApi.removeUserDetails(userId);
    }

    public UserDto updateUserDetails(UserDto userDto) {
        return userApi.updateUserDetails(userDto);
    }
}
