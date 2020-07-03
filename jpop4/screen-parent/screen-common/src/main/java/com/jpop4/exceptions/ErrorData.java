package com.jpop4.exceptions;

import java.io.Serializable;
import java.util.Objects;

public class ErrorData implements Serializable {

    private static final ErrorData EMPTY = new ErrorData(null, null);

    private final String code;
    private final String field;
    private final String message;

    public ErrorData(String field, String message, String code) {
        this.field = field;
        this.message = message;
        this.code = code;
    }

    public ErrorData(String field, String message) {
        this(field, message, null);
    }

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorData empty() {
        return EMPTY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorData that = (ErrorData) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(field, that.field) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, field, message);
    }

    @Override
    public String toString() {
        return "ErrorData{" +
                "code='" + code + '\'' +
                ", field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
