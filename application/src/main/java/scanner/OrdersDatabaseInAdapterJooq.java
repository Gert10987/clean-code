package scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.model.Tables;
import db.model.enums.Currency;
import db.model.tables.records.OrdersDescriptionRecord;
import db.model.tables.records.OrdersRecord;
import orders.Item;
import orders.Order;
import orders.OrderId;
import org.jooq.DSLContext;
import org.jooq.JSON;
import org.jooq.Record;
import org.jooq.tools.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shared.Money;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersDatabaseInAdapterJooq implements OutPorts.OrdersDatabasePort {

    @Autowired
    private DSLContext context;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(Order order) {
        var ordersRecord = new OrdersRecord();

        ordersRecord.setId(order.getId().getId());
        ordersRecord.setCurrency(Currency.lookupLiteral(order.getActualTotalPrice().getCurrency().getCurrencyCode()));
        ordersRecord.setTotalPrice(order.getActualTotalPrice().getAmount());
        ordersRecord.setTotalDiscount(order.getTotalDiscount().getAmount());

        context.insertInto(Tables.ORDERS)
                .set(ordersRecord)
                .execute();

        var orderDesc = new OrdersDescriptionRecord();

        orderDesc.setOrderId(order.getId().getId());
        orderDesc.setItems(JSON.json(JSONArray.toJSONString(order.getItems())));

        context.insertInto(Tables.ORDERS_DESCRIPTION)
                .set(orderDesc)
                .execute();
    }

    @Override
    public Optional<Order> getById(OrderId id) {

        Optional<Record> record = context.select()
                .from(Tables.ORDERS)
                .join(Tables.ORDERS_DESCRIPTION)
                .on(Tables.ORDERS.ID.eq(Tables.ORDERS_DESCRIPTION.ORDER_ID))
                .fetchOptional();

        if (record.isPresent()) {
            Record record1 = record.get();
            List<Item> items;

            try {//TODO change it
                items = objectMapper.readValue(record1.get(Tables.ORDERS_DESCRIPTION.ITEMS).toString(), new TypeReference<List<Item>>(){});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            Money totalPrice = new Money(record1.get(Tables.ORDERS.TOTAL_PRICE), java.util.Currency.getInstance(record1.get(Tables.ORDERS.CURRENCY).getLiteral()));
            Money totalDiscount = new Money(record1.get(Tables.ORDERS.TOTAL_DISCOUNT), java.util.Currency.getInstance(record1.get(Tables.ORDERS.CURRENCY).getLiteral()));
            return Optional.of(new Order(new OrderId(record1.get(Tables.ORDERS.ID)), items, totalDiscount, null, totalPrice));
        }

        return Optional.empty();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(Order order) {
        var ordersRecord = new OrdersRecord();

        ordersRecord.setId(order.getId().getId());
        ordersRecord.setCurrency(Currency.lookupLiteral(order.getActualTotalPrice().getCurrency().getCurrencyCode()));
        ordersRecord.setTotalPrice(order.getActualTotalPrice().getAmount());
        ordersRecord.setTotalDiscount(order.getTotalDiscount().getAmount());

        context.update(Tables.ORDERS)
                .set(ordersRecord)
                .execute();

        var orderDesc = new OrdersDescriptionRecord();

        orderDesc.setOrderId(order.getId().getId());
        orderDesc.setItems(JSON.json(JSONArray.toJSONString(order.getItems())));

        context.update(Tables.ORDERS_DESCRIPTION)
                .set(orderDesc)
                .execute();
    }
}
