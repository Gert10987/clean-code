package catalog;

import db.model.Tables;
import db.model.tables.records.ProductsRecord;
import org.jooq.DSLContext;
import org.jooq.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shared.Money;
import shared.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CatalogDatabaseInAdapterJooq implements OutPorts.CatalogDatabasePort {

    @Autowired
    private DSLContext context;

    @Override
    public void add(Product product) {
        ProductsRecord productEntity = new ProductsRecord();

        productEntity.setId(product.getId().getId());
        productEntity.setType(product.getProductType().toString());
        productEntity.setExpirationDate(product.getExpirationDate().atStartOfDay());

        context.insertInto(Tables.PRODUCTS)
                .set(productEntity)
                .execute();
    }

    @Override
    public Optional<Product> getById(ProductId productId) {
        return context.selectFrom(Tables.PRODUCTS)
                .where(Tables.PRODUCTS.ID.eq(productId.getId()))
                .fetchOptional()
                .map(productsRecord -> new Product(Money.PLN("1"), ProductType.MILK, toLocalDate(productsRecord.get(Tables.PRODUCTS.EXPIRATION_DATE))));
    }

    @Override
    public Optional<Product> getByProductType(ProductType productType) {
        return context.selectFrom(Tables.PRODUCTS)
                .where(Tables.PRODUCTS.TYPE.equalIgnoreCase(productType.toString()))
                .fetchOptional()
                .map(productsRecord -> new Product(Money.PLN("1"), ProductType.MILK, toLocalDate(productsRecord.get(Tables.PRODUCTS.EXPIRATION_DATE))));

    }

    private LocalDate toLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }
}
