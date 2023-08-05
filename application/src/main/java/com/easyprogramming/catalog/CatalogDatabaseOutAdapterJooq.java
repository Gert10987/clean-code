package com.easyprogramming.catalog;

import com.easyprogramming.app.db.model.Tables;
import com.easyprogramming.app.db.model.tables.records.ProductsRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyprogramming.shared.Money;
import com.easyprogramming.shared.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;

@Service
class CatalogDatabaseOutAdapterJooq implements OutPorts.CatalogDatabasePort {

    @Autowired
    private DSLContext context;

    @Override
    public void add(Product product) {
        var productEntity = toRecord(product);

        context.insertInto(Tables.PRODUCTS)
                .set(productEntity)
                .execute();
    }

    @Override
    public Optional<Product> getById(ProductId productId) {
        return context.selectFrom(Tables.PRODUCTS)
                .where(Tables.PRODUCTS.ID.eq(productId.getId()))
                .fetchOptional()
                .map(this::toDomainObject);
    }

    @Override
    public Optional<Product> getByProductType(ProductType productType) {
        return context.selectFrom(Tables.PRODUCTS)
                .where(Tables.PRODUCTS.TYPE.equalIgnoreCase(productType.toString()))
                .fetchOptional()
                .map(this::toDomainObject);

    }

    private Product toDomainObject(ProductsRecord productsRecord) {
        return Product.builder()
                .id(new ProductId(productsRecord.getId()))
                .price(new Money(productsRecord.getTotalPrice(), Currency.getInstance(productsRecord.getCurrency().getLiteral())))
                .expirationDate(toLocalDate(productsRecord.getExpirationDate()))
                .build();
    }

    private ProductsRecord toRecord(Product product) {
        var productEntity = new ProductsRecord();

        productEntity.setId(product.getId().getId());
        productEntity.setType(product.getProductType().toString());
        productEntity.setExpirationDate(product.getExpirationDate().atStartOfDay());
        return productEntity;
    }

    private LocalDate toLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }
}
