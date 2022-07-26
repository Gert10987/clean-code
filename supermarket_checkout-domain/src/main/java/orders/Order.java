package orders;

import catalog.Product;
import shared.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Order {

    private final List<Item> items = new ArrayList<>();

    private Money totalPrice = Money.ZERO_PLN();
    private final Money totalDiscount = Money.ZERO_PLN();

    private final DiscountFactory discountFactory;
    public Order(DiscountFactory discountFactory) {
        this.discountFactory = discountFactory;
    }

    public void addProduct(Product product) {

        Item item = new Item(product.getProductType(), product.getPrice());

        items.add(item);

        totalPrice = totalPrice.add(item.getPrice());

        Optional.ofNullable(discountFactory).ifPresent(dF -> applyDiscount(product, dF));

        calculate();
    }

    public Money getTotalPrice() {
        return totalPrice.minus(totalDiscount);
    }

    private void applyDiscount(Product product, DiscountFactory discountFactory) {
        List<DiscountPolicy> foundDiscountPolicies = discountFactory.getByProductType(product.getProductType());

        for (DiscountPolicy policy : foundDiscountPolicies) {
            totalDiscount.add(policy.calculateAndApply(product, items.stream()
                            .filter(item -> item.getProductType().equals(product.getProductType()))
                            .collect(Collectors.toList())));
        }
    }

    private void calculate() {
        totalPrice = items
                .stream()
                .map(Item::getTotalPrice)
                .reduce(Money.ZERO_PLN(), Money::add);
    }
}
