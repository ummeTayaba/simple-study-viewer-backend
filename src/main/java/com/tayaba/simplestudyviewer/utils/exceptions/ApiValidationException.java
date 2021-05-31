package com.tayaba.simplestudyviewer.utils.exceptions;

import org.springframework.validation.ObjectError;
import java.util.List;

public class ApiValidationException extends RuntimeException {

    private List<ObjectError> errorList;

    public ApiValidationException(List<ObjectError> errorList) {
        super("Api Validation Exception");
        this.errorList = errorList;
    }

    public ApiValidationException(String message, List<ObjectError> errorList) {
        super(message);

        this.errorList = errorList;
    }

    public List<ObjectError> getErrorList() {
        return errorList;
    }
}
