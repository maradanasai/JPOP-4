package com.jpop4.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;

public abstract class ValidationCommonService {

    @Autowired
    private ApplicationContext applicationContext;

    private ValidationResultAnalyser validationResultAnalyser;

    @Autowired
    public ValidationCommonService(ValidationResultAnalyser validationResultAnalyser) {
        this.validationResultAnalyser = validationResultAnalyser;
    }

    public void validate(Object object, Class<? extends Validator> validator) {
        if (validator != null) {
            validateCustom(object, validator);
        }
    }

    private void validateCustom(Object object, Class<? extends Validator> validatorClass) {
        if (DefaultValidator.class.equals(validatorClass)) {
            return;
        }

        if (object instanceof Collection) {
            Collection<?> collection = (Collection<?>) object;
            validateCollection(collection, validatorClass);
        } else {
            validateObject(object, validatorClass);
        }
    }

    private void validateCollection(Collection<?> collection, Class<? extends Validator> validatorClass) {
        if (collection.isEmpty()) {
            return;
        }

        Object first = collection.iterator().next();
        Validator validator = applicationContext.getBean(validatorClass);
        BindingResult allBindingResult = new BeanPropertyBindingResult(
                first, StringUtils.uncapitalize(first.getClass().getSimpleName()));

        for (Object object : collection) {
            BindingResult bindingResult = new BeanPropertyBindingResult(
                    object, StringUtils.uncapitalize(object.getClass().getSimpleName()));
            ValidationUtils.invokeValidator(validator, object, bindingResult);
            bindingResult.getAllErrors().forEach(allBindingResult::addError);
        }

        validationResultAnalyser.analyseValidationResult(allBindingResult);
    }

    private void validateObject(Object object, Class<? extends Validator> validatorClass) {
        BindingResult bindingResult = new BeanPropertyBindingResult(
                object, StringUtils.uncapitalize(object.getClass().getSimpleName()));
        validateCommon(object, validatorClass, bindingResult);
    }

    private void validateCommon(Object object,
                                Class<? extends Validator> validatorClass,
                                BindingResult bindingResult) {
        Validator validator = applicationContext.getBean(validatorClass);
        ValidationUtils.invokeValidator(validator, object, bindingResult);
        validationResultAnalyser.analyseValidationResult(bindingResult);
    }
}
