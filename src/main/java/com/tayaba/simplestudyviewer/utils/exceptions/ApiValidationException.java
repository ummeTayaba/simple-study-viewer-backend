package com.tayaba.simplestudyviewer.utils.exceptions;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import java.util.List;

public class ApiValidationException extends RuntimeException {

    private List<FieldError> errorList;

    public ApiValidationException(List<FieldError> errorList) {
        super("Api Validation Exception");
        this.errorList = errorList;
    }

    public ApiValidationException(String message, List<FieldError> errorList) {
        super(message);

        this.errorList = errorList;
    }

    public List<FieldError> getErrorList() {
        return errorList;
    }
}
