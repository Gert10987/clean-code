/*
 * This file is generated by jOOQ.
 */
package com.easyprogramming.app.db.model.tables.records;


import com.easyprogramming.app.db.model.enums.Currency;
import com.easyprogramming.app.db.model.tables.Orders;

import java.math.BigDecimal;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrdersRecord extends UpdatableRecordImpl<OrdersRecord> implements Record4<UUID, Currency, BigDecimal, BigDecimal> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>scanner_app.orders.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>scanner_app.orders.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>scanner_app.orders.currency</code>.
     */
    public void setCurrency(Currency value) {
        set(1, value);
    }

    /**
     * Getter for <code>scanner_app.orders.currency</code>.
     */
    public Currency getCurrency() {
        return (Currency) get(1);
    }

    /**
     * Setter for <code>scanner_app.orders.total_price</code>.
     */
    public void setTotalPrice(BigDecimal value) {
        set(2, value);
    }

    /**
     * Getter for <code>scanner_app.orders.total_price</code>.
     */
    public BigDecimal getTotalPrice() {
        return (BigDecimal) get(2);
    }

    /**
     * Setter for <code>scanner_app.orders.total_discount</code>.
     */
    public void setTotalDiscount(BigDecimal value) {
        set(3, value);
    }

    /**
     * Getter for <code>scanner_app.orders.total_discount</code>.
     */
    public BigDecimal getTotalDiscount() {
        return (BigDecimal) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, Currency, BigDecimal, BigDecimal> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<UUID, Currency, BigDecimal, BigDecimal> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Orders.ORDERS.ID;
    }

    @Override
    public Field<Currency> field2() {
        return Orders.ORDERS.CURRENCY;
    }

    @Override
    public Field<BigDecimal> field3() {
        return Orders.ORDERS.TOTAL_PRICE;
    }

    @Override
    public Field<BigDecimal> field4() {
        return Orders.ORDERS.TOTAL_DISCOUNT;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public Currency component2() {
        return getCurrency();
    }

    @Override
    public BigDecimal component3() {
        return getTotalPrice();
    }

    @Override
    public BigDecimal component4() {
        return getTotalDiscount();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public Currency value2() {
        return getCurrency();
    }

    @Override
    public BigDecimal value3() {
        return getTotalPrice();
    }

    @Override
    public BigDecimal value4() {
        return getTotalDiscount();
    }

    @Override
    public OrdersRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public OrdersRecord value2(Currency value) {
        setCurrency(value);
        return this;
    }

    @Override
    public OrdersRecord value3(BigDecimal value) {
        setTotalPrice(value);
        return this;
    }

    @Override
    public OrdersRecord value4(BigDecimal value) {
        setTotalDiscount(value);
        return this;
    }

    @Override
    public OrdersRecord values(UUID value1, Currency value2, BigDecimal value3, BigDecimal value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrdersRecord
     */
    public OrdersRecord() {
        super(Orders.ORDERS);
    }

    /**
     * Create a detached, initialised OrdersRecord
     */
    public OrdersRecord(UUID id, Currency currency, BigDecimal totalPrice, BigDecimal totalDiscount) {
        super(Orders.ORDERS);

        setId(id);
        setCurrency(currency);
        setTotalPrice(totalPrice);
        setTotalDiscount(totalDiscount);
    }
}
