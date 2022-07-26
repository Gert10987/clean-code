package catalog;

import catalog.exception.ExpirationDateShouldBeAfterNowException;
import shared.Money;
import shared.ProductType;

import java.time.LocalDate;
import java.util.Objects;

public class Product {

    private final ProductId id;
    private final Money price;

    private final ProductType productType;

    private LocalDate expirationDate;

    public Product(Money price, ProductType productType, LocalDate expirationDate) {
        this.id = new ProductId();
        this.price = price;

        this.productType = productType;

        setExpirationDate(expirationDate);
    }

    public ProductId getId() {
        return id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public Money getPrice() {
        return price;
    }

    private void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate.isBefore(LocalDate.now()))
            throw new ExpirationDateShouldBeAfterNowException("Expiration Date should be after now");

        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(price, product.price) && productType == product.productType
                && Objects.equals(expirationDate, product.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, productType, expirationDate);
    }

    @Override
    public String toString() {
        return "id = " + id + " " + "price = " + price + " " + "productType = " + productType + " " + "expirationDate = " + expirationDate + " ";
    }
}
