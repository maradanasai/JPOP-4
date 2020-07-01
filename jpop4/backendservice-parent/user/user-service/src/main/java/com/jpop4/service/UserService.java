package com.jpop4.service;

import com.jpop4.api.user.UserApi;
import com.jpop4.converter.UserMapper;
import com.jpop4.domain.repository.UserRepository;
import com.jpop4.dto.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserService implements UserApi {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> findAllUserDetails() {
        return userMapper
                .toUserDtos(userRepository.findAll());
    }

    @Override
    public UserDto findUserDetails(BigInteger userId) {
        return userMapper
                .toUserDto(userRepository
                        .findById(userId)
                        .orElse(null));
    }

    @Override
    public boolean addUserDetails(UserDto userDto) {
        userRepository.save(userMapper.toUser(userDto));
        LOG.debug("User details {} saved successfully", userDto);
        return true;
    }

    @Override
    public boolean removeUserDetails(BigInteger id) {
        userRepository.deleteById(id);
        LOG.debug("User details with id {} deleted successfully", id);
        return true;
    }

    @Override
    public UserDto updateUserDetails(UserDto userDto) {
        userRepository.save(userMapper.toUser(userDto));
        LOG.debug("User details {} updated successfully", userDto);
        return userDto;
    }
}
