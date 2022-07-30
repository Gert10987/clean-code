package orders;

import catalog.Product;
import lombok.*;
import shared.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
    @Getter
    private final OrderId id;
    @Getter
    private final List<Item> items;
    @Getter
    private final Money totalDiscount;
    private final DiscountFactory discountFactory;

    private Money totalPrice = Money.ZERO_PLN();

    public Order(DiscountFactory discountFactory) {
        this.id = new OrderId();
        this.discountFactory = discountFactory;

        this.totalDiscount = Money.ZERO_PLN();
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product) {

        Item item = new Item(product.getProductType(), product.getPrice());

        items.add(item);

        totalPrice.add(item.getPrice());

        Optional.ofNullable(discountFactory).ifPresent(dF -> applyDiscount(product, dF));

        calculate();
    }

    public Money getActualTotalPrice() {
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
                .map(Item::getPriceMinusDiscount)
                .reduce(Money.ZERO_PLN(), Money::add);
    }
}
