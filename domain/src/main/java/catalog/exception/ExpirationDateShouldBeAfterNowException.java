package catalog.exception;

import shared.exception.DomainException;

public class ExpirationDateShouldBeAfterNowException extends DomainException {

    public ExpirationDateShouldBeAfterNowException(String message) {
        super(message);
    }
}
