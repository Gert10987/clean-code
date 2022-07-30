package com.easyprogramming.scanner.exception;

import com.easyprogramming.shared.exception.DomainException;

public class BillingIsNotPresentException extends DomainException {

    public BillingIsNotPresentException(String message, Object... args) {
        super(message, args);
    }
}
