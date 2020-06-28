package com.jpop4.exceptions;

import com.jpop4.i18n.MessageResolver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalBEControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalBEControllerExceptionHandler.class);
    public static final String ERROR_SYSTEM_BACKEND = "error.system.backend";

    @Value("${exception.trace.enabled:false}")
    private boolean exceptionTraceEnabled;

    @Autowired
    protected MessageResolver messageResolver;


    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleDetailedValidationException(ValidationException exception) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Validation exception", exception);
        }

        LOG.info(exception.toString());

        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrors(toErrorDtoList(exception.getErrors()));
        setStackTrace(errorDto, exception);

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleApplicationExceptions(RuntimeException exception) {
        ErrorDto errorDto = buildSystemErrorDto(exception);
        LOG.error("Unhandled server Exception  with code {} has been caught at time {} ", errorDto.getErrorCode(),
                errorDto.getErrorTime(), exception);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<ErrorData> toErrorDtoList(List<ErrorData> errors) {
        return errors.stream()
                .map(error -> new ErrorData(error.getField(), error.getMessage(), error.getCode()))
                .collect(Collectors.toList());
    }

    private ErrorDto buildSystemErrorDto(Exception exception) {
        ErrorDto errorDto = new ErrorDto(messageResolver.getMessage(ERROR_SYSTEM_BACKEND));
        setStackTrace(errorDto, exception);
        return errorDto;
    }

    /**
     * Sets the stacktrace of exception if it's configured
     */
    private void setStackTrace(ErrorDto errorDto, Exception exception) {
        if (exceptionTraceEnabled) {
            errorDto.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
    }
}
