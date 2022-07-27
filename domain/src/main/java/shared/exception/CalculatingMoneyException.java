package shared.exception;

public class CalculatingMoneyException extends DomainException {

    public static final DomainException AMOUNT_MONEY_SHOULD_BE_GREATER_THAN_0 = new CalculatingMoneyException("Amount of money should be greater than 0");
    public static final DomainException ONLY_PL_CURRENCY_IS_SUPPORTED = new CalculatingMoneyException("Only pl currency is supported");

    CalculatingMoneyException(String message) {
        super(message);
    }
}
