package com.jpop4.exceptions;

import java.util.Collections;
import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<ErrorData> errors;

    public ValidationException(List<ErrorData> data) {
        this.errors = data;
    }

    public ValidationException(List<ErrorData> data, Throwable cause) {
        super(cause);
        this.errors = data;
    }

    public ValidationException(ErrorData errorData) {
        this(Collections.singletonList(errorData));
    }

    public ValidationException(String message, String code, String fieldName) {
        this(new ErrorData(fieldName, message, code));
    }

    public ValidationException(String message, String code, String fieldName, Throwable e) {
        super(e);
        errors = Collections.singletonList(new ErrorData(fieldName, message, code));
    }

    public ValidationException(String message, String fieldName) {
        this(message, null, fieldName);
    }

    public ValidationException(String message, String fieldName, Throwable e) {
        super(e);
        this.errors = Collections.singletonList(new ErrorData(fieldName, message, null));
    }

    public ValidationException(String message) {
        this(new ErrorData(null, message, null));
    }

    public ValidationException(String message, Throwable e) {
        super(e);
        errors = Collections.singletonList(new ErrorData(null, message, null));
    }

    public List<ErrorData> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "errors=" + errors +
                '}';
    }
}
