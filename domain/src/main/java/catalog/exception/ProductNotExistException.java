package catalog.exception;

import shared.exception.DomainException;

public class ProductNotExistException extends DomainException {

    public ProductNotExistException(String message, Object... args) {
        super(message, args);
    }
}
