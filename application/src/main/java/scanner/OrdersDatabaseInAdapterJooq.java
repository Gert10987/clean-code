package scanner;

import catalog.Product;
import catalog.ProductId;
import db.model.Tables;
import db.model.tables.records.ProductsRecord;
import orders.Order;
import orders.OrderId;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shared.Money;
import shared.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrdersDatabaseInAdapterJooq implements OutPorts.OrdersDatabasePort {

    @Autowired
    private DSLContext context;

    @Override
    public void add(Order order) {
//        ProductsRecord productEntity = new ProductsRecord();
//
//        productEntity.setId(product.getId().getId());
//        productEntity.setType(product.getProductType().toString());
//        productEntity.setExpirationDate(product.getExpirationDate().atStartOfDay());
//
//        context.insertInto(Tables.PRODUCTS)
//                .set(productEntity)
//                .execute();
    }

    @Override
    public Optional<Order> getById(OrderId id) {
        return Optional.empty();
//        return context.selectFrom(Tables.PRODUCTS).where(Tables.PRODUCTS.ID.eq(productId.getId()))
//                .fetchOptional()
//                .map(productsRecord -> new Product(Money.PLN("1"), ProductType.MILK, toLocalDate(productsRecord.get(Tables.PRODUCTS.EXPIRATION_DATE))));
    }
}
