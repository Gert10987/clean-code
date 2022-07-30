package com.easyprogramming.catalog.exception;

import com.easyprogramming.shared.exception.DomainException;

public class ExpirationDateShouldBeAfterNowException extends DomainException {

    public ExpirationDateShouldBeAfterNowException(String message) {
        super(message);
    }
}
