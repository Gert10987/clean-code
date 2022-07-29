package orders;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import shared.Money;
import shared.ProductType;

@EqualsAndHashCode
@ToString
public class Item {

    private final ProductType productType;

    private final Money price;
    private Money discount;

    Item(ProductType productType, Money price) {
        this.productType = productType;
        this.price = price;

        this.discount = Money.ZERO_PLN();
    }

    void setDiscount(Money discount) {
        this.discount = discount;
    }

    Money getPrice() {
        return price;
    }

    Money getTotalPrice() {
        return price.minus(discount);
    }

    public ProductType getProductType() {
        return productType;
    }
}
