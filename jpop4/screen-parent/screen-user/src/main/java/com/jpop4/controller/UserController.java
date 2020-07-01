package com.jpop4.controller;

import com.jpop4.dto.user.UserDto;
import com.jpop4.service.UserScreenService;
import com.jpop4.validation.DtoValidator;
import com.jpop4.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserScreenService userScreenService;

    @GetMapping("/{userId}")
    public UserDto findUserDetails(@PathVariable BigInteger userId) {
        return userScreenService.getUserDetails(userId);
    }

    @GetMapping
    public List<UserDto> findAllUserDetails() {
        return userScreenService.getAllUserDetails();
    }

    @PostMapping
    public boolean addUserDetails(
            @DtoValidator(customValidator = UserValidator.class)
            @RequestBody UserDto userDto) {
        return userScreenService.addUserDetails(userDto);
    }

    @DeleteMapping("/{userId}")
    public boolean removeUserDetails(@PathVariable BigInteger userId) {
        return userScreenService.deleteUserDetails(userId);
    }

    @PutMapping
    public UserDto updateUserDetails(
            @DtoValidator(customValidator = UserValidator.class)
            @RequestBody UserDto userDto) {
        return userScreenService.updateUserDetails(userDto);
    }
}
