package com.easyprogramming.scanner;

import com.easyprogramming.app.db.model.Tables;
import com.easyprogramming.app.db.model.enums.Currency;
import com.easyprogramming.app.db.model.tables.records.OrdersDescriptionRecord;
import com.easyprogramming.app.db.model.tables.records.OrdersRecord;
import com.easyprogramming.orders.Order;
import com.easyprogramming.orders.OrderId;
import com.easyprogramming.shared.ProductType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import org.jooq.JSON;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Currency.getInstance;

@Service
class OrdersDatabaseOutAdapterJooq implements OutPorts.OrdersDatabasePort {

    @Autowired
    private DSLContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(Order order) {
        upsert(order);
    }

    @Override
    public Optional<Order> getById(OrderId id) {

        Optional<Record> dbRes = context.select()
                .from(Tables.ORDERS)
                .join(Tables.ORDERS_DESCRIPTION)
                .on(Tables.ORDERS.ID.eq(Tables.ORDERS_DESCRIPTION.ORDER_ID))
                .where(Tables.ORDERS.ID.eq(id.getId()))
                .fetchOptional();

        return dbRes.map(this::toDomainObject);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(Order order) {
        upsert(order);
    }

    private void upsert(Order order) {

        OrdersRecord ordersRecord = toOrderRecord(order);

        context.insertInto(Tables.ORDERS)
                .set(ordersRecord)
                .onDuplicateKeyUpdate()
                .set(ordersRecord)
                .execute();


        OrdersDescriptionRecord ordersDescriptionRecord = toOrderDescriptionRecord(order);

        context.insertInto(Tables.ORDERS_DESCRIPTION)
                .set(ordersDescriptionRecord)
                .onDuplicateKeyUpdate()
                .set(ordersDescriptionRecord)
                .execute();
    }

    private OrdersRecord toOrderRecord(Order order) {
        var ordersRecord = new OrdersRecord();

        ordersRecord.setId(order.getId().getId());
        ordersRecord.setCurrency(Currency.lookupLiteral(order.getActualTotalPrice().getCurrency().getCurrencyCode()));
        ordersRecord.setTotalPrice(order.getActualTotalPrice().getAmount());
        ordersRecord.setTotalDiscount(order.getTotalDiscount().getAmount());

        return ordersRecord;
    }

    private OrdersDescriptionRecord toOrderDescriptionRecord(Order order) {

        var orderDesc = new OrdersDescriptionRecord();
        orderDesc.setOrderId(order.getId().getId());

        try {
            orderDesc.setItems(JSON.jsonOrNull(objectMapper.writeValueAsString(order.getItems()
                    .stream()
                    .map(OrdersDatabaseOutAdapterJooq::toItemRecord)
                    .collect(Collectors.toList()))));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return orderDesc;
    }

    private static Item toItemRecord(com.easyprogramming.orders.Item item) {
        return new Item(item.getProductType(),
                new Money(item.getPrice().getAmount(), item.getPrice().getCurrency()),
                new Money(item.getDiscount().getAmount(), item.getDiscount().getCurrency()));
    }

    private Order toDomainObject(Record record) {

        List<Item> items = extractItems(record);

        com.easyprogramming.shared.Money totalPrice = new com.easyprogramming.shared.Money(record.get(Tables.ORDERS.TOTAL_PRICE),
                getInstance(record.get(Tables.ORDERS.CURRENCY).getLiteral()));
        com.easyprogramming.shared.Money totalDiscount = new com.easyprogramming.shared.Money(record.get(Tables.ORDERS.TOTAL_DISCOUNT),
                getInstance(record.get(Tables.ORDERS.CURRENCY).getLiteral()));

        return Order.builder()
                .id(new OrderId(record.get(Tables.ORDERS.ID)))
                .items(toDomainItems(items))
                .totalPrice(totalPrice)
                .totalDiscount(totalDiscount)
                .build();
    }

    private List<com.easyprogramming.orders.Item> toDomainItems(List<Item> items) {
        return items.stream()
                .map(itemDb -> new com.easyprogramming.orders.Item(itemDb.productType(),
                        new com.easyprogramming.shared.Money(itemDb.price().amount(), itemDb.price().currency()),
                        new com.easyprogramming.shared.Money(itemDb.discount().amount(), itemDb.discount().currency())))
                .collect(Collectors.toList());
    }

    private List<Item> extractItems(Record record) {
        List<Item> items;

        try {
            items = objectMapper.readValue(record.get(Tables.ORDERS_DESCRIPTION.ITEMS).data(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    private record Item(ProductType productType,
                        OrdersDatabaseOutAdapterJooq.Money price,
                        OrdersDatabaseOutAdapterJooq.Money discount) {
    }

    private record Money(BigDecimal amount, java.util.Currency currency) {
    }
}

