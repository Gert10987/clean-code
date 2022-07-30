/*
 * This file is generated by jOOQ.
 */
package com.easyprogramming.app.db.model.tables.records;


import com.easyprogramming.app.db.model.tables.OrdersDescription;

import java.util.UUID;

import org.jooq.Field;
import org.jooq.JSON;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrdersDescriptionRecord extends UpdatableRecordImpl<OrdersDescriptionRecord> implements Record2<UUID, JSON> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>scanner_app.orders_description.order_id</code>.
     */
    public void setOrderId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>scanner_app.orders_description.order_id</code>.
     */
    public UUID getOrderId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>scanner_app.orders_description.items</code>.
     */
    public void setItems(JSON value) {
        set(1, value);
    }

    /**
     * Getter for <code>scanner_app.orders_description.items</code>.
     */
    public JSON getItems() {
        return (JSON) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<UUID, JSON> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<UUID, JSON> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return OrdersDescription.ORDERS_DESCRIPTION.ORDER_ID;
    }

    @Override
    public Field<JSON> field2() {
        return OrdersDescription.ORDERS_DESCRIPTION.ITEMS;
    }

    @Override
    public UUID component1() {
        return getOrderId();
    }

    @Override
    public JSON component2() {
        return getItems();
    }

    @Override
    public UUID value1() {
        return getOrderId();
    }

    @Override
    public JSON value2() {
        return getItems();
    }

    @Override
    public OrdersDescriptionRecord value1(UUID value) {
        setOrderId(value);
        return this;
    }

    @Override
    public OrdersDescriptionRecord value2(JSON value) {
        setItems(value);
        return this;
    }

    @Override
    public OrdersDescriptionRecord values(UUID value1, JSON value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrdersDescriptionRecord
     */
    public OrdersDescriptionRecord() {
        super(OrdersDescription.ORDERS_DESCRIPTION);
    }

    /**
     * Create a detached, initialised OrdersDescriptionRecord
     */
    public OrdersDescriptionRecord(UUID orderId, JSON items) {
        super(OrdersDescription.ORDERS_DESCRIPTION);

        setOrderId(orderId);
        setItems(items);
    }
}
