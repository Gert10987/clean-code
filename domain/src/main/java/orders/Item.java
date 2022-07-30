package orders;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import shared.Money;
import shared.ProductType;

@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
public class Item {

    private ProductType productType;

    private Money price;
    private Money discount;

    Item(ProductType productType, Money price) {
        this.productType = productType;
        this.price = price;

        this.discount = Money.ZERO_PLN();
    }

    void setDiscount(Money discount) {
        this.discount = discount;
    }

    public Money getPriceMinusDiscount() {
        return price.minus(discount);
    }
}
