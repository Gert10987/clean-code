package orders;

import shared.Money;
import shared.ProductType;

import java.util.Objects;

class Item {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return productType == item.productType && Objects.equals(price, item.price) && Objects.equals(discount, item.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, price, discount);
    }
}
