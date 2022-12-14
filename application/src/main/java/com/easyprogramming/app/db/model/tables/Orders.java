/*
 * This file is generated by jOOQ.
 */
package com.easyprogramming.app.db.model.tables;


import com.easyprogramming.app.db.model.Keys;
import com.easyprogramming.app.db.model.ScannerApp;
import com.easyprogramming.app.db.model.enums.Currency;
import com.easyprogramming.app.db.model.tables.records.OrdersRecord;

import java.math.BigDecimal;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Orders extends TableImpl<OrdersRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>scanner_app.orders</code>
     */
    public static final Orders ORDERS = new Orders();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrdersRecord> getRecordType() {
        return OrdersRecord.class;
    }

    /**
     * The column <code>scanner_app.orders.id</code>.
     */
    public final TableField<OrdersRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>scanner_app.orders.currency</code>.
     */
    public final TableField<OrdersRecord, Currency> CURRENCY = createField(DSL.name("currency"), SQLDataType.VARCHAR.asEnumDataType(com.easyprogramming.app.db.model.enums.Currency.class), this, "");

    /**
     * The column <code>scanner_app.orders.total_price</code>.
     */
    public final TableField<OrdersRecord, BigDecimal> TOTAL_PRICE = createField(DSL.name("total_price"), SQLDataType.NUMERIC, this, "");

    /**
     * The column <code>scanner_app.orders.total_discount</code>.
     */
    public final TableField<OrdersRecord, BigDecimal> TOTAL_DISCOUNT = createField(DSL.name("total_discount"), SQLDataType.NUMERIC, this, "");

    private Orders(Name alias, Table<OrdersRecord> aliased) {
        this(alias, aliased, null);
    }

    private Orders(Name alias, Table<OrdersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>scanner_app.orders</code> table reference
     */
    public Orders(String alias) {
        this(DSL.name(alias), ORDERS);
    }

    /**
     * Create an aliased <code>scanner_app.orders</code> table reference
     */
    public Orders(Name alias) {
        this(alias, ORDERS);
    }

    /**
     * Create a <code>scanner_app.orders</code> table reference
     */
    public Orders() {
        this(DSL.name("orders"), null);
    }

    public <O extends Record> Orders(Table<O> child, ForeignKey<O, OrdersRecord> key) {
        super(child, key, ORDERS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ScannerApp.SCANNER_APP;
    }

    @Override
    public UniqueKey<OrdersRecord> getPrimaryKey() {
        return Keys.ORDERS_PKEY;
    }

    @Override
    public Orders as(String alias) {
        return new Orders(DSL.name(alias), this);
    }

    @Override
    public Orders as(Name alias) {
        return new Orders(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Orders rename(String name) {
        return new Orders(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Orders rename(Name name) {
        return new Orders(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, Currency, BigDecimal, BigDecimal> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
