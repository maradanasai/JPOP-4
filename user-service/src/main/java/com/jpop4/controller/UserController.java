package com.jpop4.controller;

import com.jpop4.domain.UserDto;
import com.jpop4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDto findUserDetails(@PathVariable BigInteger userId) {
        return userService.getUserDetails(userId);
    }

    @GetMapping
    public List<UserDto> findAllUserDetails() {
        return userService.getAllUserDetails();
    }

    @PostMapping
    public boolean addUserDetails(@RequestBody UserDto userDto) {
        return userService.addUserDetails(userDto);
    }

    @DeleteMapping("/{userId}")
    public boolean removeUserDetails(@PathVariable BigInteger userId) {
        return userService.deleteUserDetails(userId);
    }

    @PutMapping
    public UserDto updateUserDetails(@RequestBody UserDto userDto) {
        return userService.updateUserDetails(userDto);
    }
}
