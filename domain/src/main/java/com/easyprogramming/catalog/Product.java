package com.easyprogramming.catalog;

import com.easyprogramming.catalog.exception.ExpirationDateShouldBeAfterNowException;
import lombok.*;
import com.easyprogramming.shared.Money;
import com.easyprogramming.shared.ProductType;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    private void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate.isBefore(LocalDate.now())) {
            throw new ExpirationDateShouldBeAfterNowException("Expiration Date should be after now");
        }

        this.expirationDate = expirationDate;
    }
}
