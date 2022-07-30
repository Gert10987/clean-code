package com.easyprogramming.catalog.exception;

import com.easyprogramming.shared.exception.DomainException;

public class ProductNotExistException extends DomainException {

    public ProductNotExistException(String message, Object... args) {
        super(message, args);
    }
}
