package orders;

import catalog.Product;
import shared.Money;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountPolicy {
    Money calculateAndApply(Product product, List<Item> size);
}

class Second50PercentCheaperPolicy implements DiscountPolicy {

    @Override
    public Money calculateAndApply(Product product, List<Item> currentItems) {

        Money res = Money.ZERO_PLN();

        if (currentItems.size() >= 2) {
            for (int i = 0; i < 1; i++) {
                Money discount = get50percentOfOriginalPrice(product);
                currentItems.get(i).setDiscount(discount);
                res = res.add(discount);
            }
        }
        return res;
    }

    private static Money get50percentOfOriginalPrice(Product product) {
        return Money.PLN(product.getPrice().getAmount().multiply(new BigDecimal("0.5")));
    }
}

class FreeForSpecificAmountInOrderPolicy implements DiscountPolicy {

    private final int exist;
    private final int amountFree;

    FreeForSpecificAmountInOrderPolicy(int exist, int amountFree) {
        this.exist = exist;
        this.amountFree = amountFree;
    }

    @Override
    public Money calculateAndApply(Product product, List<Item> currentItems) {

        Money res = Money.ZERO_PLN();

        if (currentItems.size() >= exist) {
            for (int i = 0; i < amountFree; i++) {
                currentItems.get(i).setDiscount(product.getPrice());
                res = res.add(product.getPrice());
            }
        }

        return res;
    }
}
