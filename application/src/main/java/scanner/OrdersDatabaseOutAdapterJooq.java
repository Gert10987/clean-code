package scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.model.Tables;
import db.model.enums.Currency;
import db.model.tables.records.OrdersDescriptionRecord;
import db.model.tables.records.OrdersRecord;
import orders.Order;
import orders.OrderId;
import org.jooq.DSLContext;
import org.jooq.JSON;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shared.ProductType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersDatabaseOutAdapterJooq implements OutPorts.OrdersDatabasePort {

    @Autowired
    private DSLContext context;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(Order order) {
        upsert(order);
    }

    @Override
    public Optional<Order> getById(OrderId id) {

        Optional<Record> record = context.select()
                .from(Tables.ORDERS)
                .join(Tables.ORDERS_DESCRIPTION)
                .on(Tables.ORDERS.ID.eq(Tables.ORDERS_DESCRIPTION.ORDER_ID))
                .where(Tables.ORDERS.ID.eq(id.getId()))
                .fetchOptional();

        if (record.isPresent()) {
            Record record1 = record.get();
            List<Item> items;
//            List<BernOBJ> denoObj = mapper.readValue(denotations,
//                    mapper.getTypeFactory().constructCollectionType(List.class, BernObj.class));
            try {//TODO change it
                items = objectMapper.readValue(record1.get(Tables.ORDERS_DESCRIPTION.ITEMS).data(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            shared.Money totalPrice = new shared.Money(record1.get(Tables.ORDERS.TOTAL_PRICE),
                    java.util.Currency.getInstance(record1.get(Tables.ORDERS.CURRENCY).getLiteral()));
            shared.Money totalDiscount = new shared.Money(record1.get(Tables.ORDERS.TOTAL_DISCOUNT),
                    java.util.Currency.getInstance(record1.get(Tables.ORDERS.CURRENCY).getLiteral()));
            return Optional.of(new Order(new OrderId(record1.get(Tables.ORDERS.ID)), items.stream().map(
                    itemDb -> new orders.Item(itemDb.productType(),
                            new shared.Money(itemDb.price().amount(), itemDb.price().currency()),
                            new shared.Money(itemDb.discount().amount(), itemDb.discount().currency()))).collect(
                    Collectors.toList()), totalDiscount, null, totalPrice));
        }

        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(Order order) {
        upsert(order);
    }

    void upsert(Order order) {
        var ordersRecord = new OrdersRecord();

        ordersRecord.setId(order.getId().getId());
        ordersRecord.setCurrency(Currency.lookupLiteral(order.getActualTotalPrice().getCurrency().getCurrencyCode()));
        ordersRecord.setTotalPrice(order.getActualTotalPrice().getAmount());
        ordersRecord.setTotalDiscount(order.getTotalDiscount().getAmount());

        context.insertInto(Tables.ORDERS)
                .set(ordersRecord)
                .onDuplicateKeyUpdate()
                .set(ordersRecord)
                .execute();

        var orderDesc = new OrdersDescriptionRecord();

        orderDesc.setOrderId(order.getId().getId());
        try {
            orderDesc.setItems(JSON.jsonOrNull(objectMapper.writeValueAsString(order.getItems().stream().map(
                    item -> new Item(item.getProductType(),
                            new Money(item.getPrice().getAmount(), item.getPrice().getCurrency()),
                            new Money(item.getDiscount().getAmount(), item.getDiscount().getCurrency())))
                    .collect(Collectors.toList()))));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        context.insertInto(Tables.ORDERS_DESCRIPTION)
                .set(orderDesc)
                .onDuplicateKeyUpdate()
                .set(orderDesc)
                .execute();
    }

    private record Item(ProductType productType,
                        OrdersDatabaseOutAdapterJooq.Money price,
                        OrdersDatabaseOutAdapterJooq.Money discount) {
    }

    private record Money(BigDecimal amount, java.util.Currency currency) {
    }
}

