package com.jpop4.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ValidationService extends ValidationCommonService {

    @Autowired
    public ValidationService(@Qualifier("validationResultAnalyser") ValidationResultAnalyser validationResultAnalyser) {
        super(validationResultAnalyser);
    }
}
