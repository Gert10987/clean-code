package com.easyprogramming.app.exception;

import com.easyprogramming.catalog.exception.ProductNotExistException;
import com.easyprogramming.shared.exception.DomainException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO Extend error handling
    @ExceptionHandler(value = {ProductNotExistException.class})
    protected ResponseEntity<Object> handleConflict(ProductNotExistException ex, WebRequest request) {
        var bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {DomainException.class})
    protected ResponseEntity<Object> handleConflict(DomainException ex, WebRequest request) {
        var bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}