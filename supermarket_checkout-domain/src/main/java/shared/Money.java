package shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import shared.exception.CalculatingMoneyException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@EqualsAndHashCode
@ToString
@Getter
public class Money {

    private BigDecimal amount;
    private Currency currency;

    public Money(String amount, Currency currency) {
        setAmount(new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN));
        setCurrency(currency);
    }

    private Money(BigDecimal amount, Currency currency) {
        setAmount(amount.setScale(2, RoundingMode.HALF_EVEN));
        setCurrency(currency);
    }

    public static Money PLN(String amount) {
        return new Money(new BigDecimal(amount), Currency.getInstance("PLN"));
    }

    public static Money PLN(BigDecimal amount) {
        return new Money(amount, Currency.getInstance("PLN"));
    }

    public static Money ZERO_PLN() {
        return PLN("0");
    }

    public Money add(Money money) {
        return new Money(amount.add(money.getAmount()), getCurrency());
    }

    public Money minus(Money money) {
        return new Money(amount.subtract(money.getAmount()), getCurrency());
    }

    private void setAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw CalculatingMoneyException.AMOUNT_MONEY_SHOULD_BE_GREATER_THAN_0;
        }

        this.amount = amount;
    }

    private void setCurrency(Currency currency) {
        try {
            if (!currency.equals(Currency.getInstance("PLN"))) {
                throw CalculatingMoneyException.ONLY_PL_CURRENCY_IS_SUPPORTED;
            }
        } catch (IllegalArgumentException e) {
            throw CalculatingMoneyException.ONLY_PL_CURRENCY_IS_SUPPORTED;
        }
        this.currency = currency;
    }
}
