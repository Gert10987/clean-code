package scanner.exception;

import shared.exception.DomainException;

public class OrderNotFoundException extends DomainException {

    public OrderNotFoundException(String message, Object... args) {
        super(message, args);
    }
}
