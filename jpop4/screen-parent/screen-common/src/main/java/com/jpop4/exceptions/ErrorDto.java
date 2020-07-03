package com.jpop4.exceptions;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.jpop4.constants.DateConstants.DATE_FORMATTER_MMDDYY_HMMSS;

public class ErrorDto {


    private List<ErrorData> errors;

    private String stackTrace;
    private String errorTime = DATE_FORMATTER_MMDDYY_HMMSS.format(LocalDateTime.now());
    
    // TODO TMP solution, permanent solution should be design and implemented to bring a real value
    private String errorCode = UUID.randomUUID().toString();

    public ErrorDto() {
    }


    public ErrorDto(String message) {
        this(message, null);
    }

    public ErrorDto(String message, String stackTrace) {
        this(Collections.singletonList(new ErrorData(null, message, null)), stackTrace);
    }

    public ErrorDto(List<ErrorData> errors, String stackTrace) {
        this.errors = errors;
        this.stackTrace = stackTrace;
    }

    public void setErrors(List<ErrorData> errors) {
        this.errors = errors;
    }

    public List<ErrorData> getErrors() {
        return errors;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(String errorTime) {
        this.errorTime = errorTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
