package com.jpop4.validator;

import com.jpop4.dto.book.BookDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigInteger;
import java.util.Objects;

@Component
public class BookValidator implements Validator {

    private static final String EMPTY = "";

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_AUTHOR = "author";

    private static final String ERROR_INVALID_BOOK_ID = "error.invalid.book.id";
    private static final String ERROR_INVALID_BOOK_NAME = "error.invalid.book.name";
    private static final String ERROR_INVALID_BOOK_AUTHOR = "error.invalid.book.author";

    @Override
    public boolean supports(Class<?> clazz) {
        return BookDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDto bookDto = (BookDto) target;

        if (Objects.isNull(bookDto.getId()) || bookDto.getId().compareTo(BigInteger.ZERO) <= 0) {
            errors.rejectValue(FIELD_ID, EMPTY, ERROR_INVALID_BOOK_ID);
        }

        if (Objects.isNull(bookDto.getName()) || bookDto.getName().isEmpty()) {
            errors.rejectValue(FIELD_NAME, EMPTY, ERROR_INVALID_BOOK_NAME);
        }

        if (Objects.isNull(bookDto.getAuthor()) || bookDto.getAuthor().isEmpty()) {
            errors.rejectValue(FIELD_AUTHOR, EMPTY, ERROR_INVALID_BOOK_AUTHOR);
        }
    }
}
