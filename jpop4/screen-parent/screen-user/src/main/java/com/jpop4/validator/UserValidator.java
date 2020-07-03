package com.jpop4.validator;

import com.jpop4.dto.user.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigInteger;
import java.util.Objects;

@Component
public class UserValidator implements Validator {

    private static final String EMPTY = "";

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PHONE_NO = "phoneNo";

    private static final String ERROR_INVALID_USER_ID = "error.invalid.user.id";
    private static final String ERROR_INVALID_USER_NAME = "error.invalid.user.name";
    private static final String ERROR_INVALID_PHONE_NUMBER = "error.invalid.user.phone.no";

    private static final String PHONE_NO_VALIDATION_REGEX = "^[6-9]\\d{9}$";

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        if (Objects.isNull(userDto.getId()) || userDto.getId().compareTo(BigInteger.ZERO) <= 0) {
            errors.rejectValue(FIELD_ID, EMPTY, ERROR_INVALID_USER_ID);
        }

        if (Objects.isNull(userDto.getName()) || userDto.getName().isEmpty()) {
            errors.rejectValue(FIELD_NAME, EMPTY, ERROR_INVALID_USER_NAME);
        }

        if (Objects.isNull(userDto.getPhoneNo()) ||
                userDto.getPhoneNo().isEmpty() ||
                !userDto.getPhoneNo().matches(PHONE_NO_VALIDATION_REGEX)) {
            errors.rejectValue(FIELD_PHONE_NO, EMPTY, ERROR_INVALID_PHONE_NUMBER);
        }
    }
}
