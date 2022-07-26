package shared.exception;

public abstract class DomainException extends RuntimeException {

    public DomainException(String message, Object ...args) {
        super(String.format(message, args));
    }

    public DomainException(String message, Throwable cause, Object ...args) {
        super(String.format(message, args), cause);
    }
}
