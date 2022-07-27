package scanner.exception;

import shared.exception.DomainException;

public class BillingIsNotPresentException extends DomainException {

    public BillingIsNotPresentException(String message, Object... args) {
        super(message, args);
    }
}
