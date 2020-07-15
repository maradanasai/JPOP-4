package com.jpop4.service;

import com.jpop4.domain.UserDto;
import com.jpop4.mapper.UserMapper;
import com.jpop4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto getUserDetails(BigInteger userId) {
        return userMapper.toUserDto(
                userRepository.findById(userId)
                        .orElse(null)
        );
    }

    public List<UserDto> getAllUserDetails() {
        return userMapper.toUserDtos(userRepository.findAll());
    }

    public boolean addUserDetails(UserDto userDto) {
        userRepository.save(userMapper.toUser(userDto));
        return true;
    }

    public boolean deleteUserDetails(BigInteger userId) {
        userRepository.deleteById(userId);
        return true;
    }

    public UserDto updateUserDetails(UserDto userDto) {
        userRepository.save(userMapper.toUser(userDto));
        return userDto;
    }
}
