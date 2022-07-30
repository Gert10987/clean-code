package com.easyprogramming.scanner.exception;

import com.easyprogramming.shared.exception.DomainException;

public class OrderNotFoundException extends DomainException {

    public OrderNotFoundException(String message, Object... args) {
        super(message, args);
    }
}
