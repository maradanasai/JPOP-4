package com.jpop4.validation;


import com.jpop4.error.message.ErrorMessage;
import com.jpop4.error.resolver.ErrorResolver;
import com.jpop4.exceptions.ErrorData;
import com.jpop4.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Qualifier("validationResultAnalyser")
public class ValidationResultAnalyser {

    @Autowired
    private ErrorResolver errorResolver;

    public void analyseValidationResult(Errors bindingResult) {
        List<ErrorData> errors = bindingResult.getFieldErrors()
                .stream()
                .map(this::buildErrorData)
                .collect(Collectors.toList());

        errors.addAll(bindingResult.getGlobalErrors()
                .stream()
                .map(this::buildErrorData)
                .collect(Collectors.toList()));

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    protected ErrorData buildErrorData(FieldError error) {
        ErrorMessage message = errorMessage(error.getDefaultMessage(), prepareArguments(error));
        return new ErrorData(error.getField(), textFrom(message), codeFrom(message));
    }

    protected ErrorData buildErrorData(ObjectError error) {
        ErrorMessage message = errorMessage(error.getDefaultMessage(), error.getArguments());
        return new ErrorData(null, textFrom(message), codeFrom(message));
    }

    protected Object[] prepareArguments(FieldError fieldError) {
        Object[] args = fieldError.getArguments();
        if (args == null) {
            Object value = fieldError.getRejectedValue();
            if (value == null) {
                value = "";
            }
            args = new Object[]{value};
        }
        return args;
    }

    protected ErrorMessage errorMessage(String key, Object[] args) {
        ErrorMessage result = null;
        if (key != null) {
            result = errorResolver.getErrorMessageFromProperties(
                    key, Optional.ofNullable(args).orElse(new Object[]{}));
        }
        return result;
    }

    protected String textFrom(ErrorMessage errorMessage) {
        return Optional.ofNullable(errorMessage).map(ErrorMessage::getMessage).orElse(null);
    }

    protected String codeFrom(ErrorMessage errorMessage) {
        return Optional.ofNullable(errorMessage).map(ErrorMessage::getCode).orElse(null);
    }

}
