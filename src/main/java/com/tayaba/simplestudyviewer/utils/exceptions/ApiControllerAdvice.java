package com.tayaba.simplestudyviewer.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiValidationException.class)
    public ResponseEntity<Object> handleApiValidationException(ApiValidationException ex, WebRequest request) {

        LinkedHashMap<String, String> errorMap = ex.getErrorList().stream()
                .collect(
                        LinkedHashMap::new,
                        (map, item) -> map.put(item.getObjectName(), item.getDefaultMessage()),
                        Map::putAll
                );

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
