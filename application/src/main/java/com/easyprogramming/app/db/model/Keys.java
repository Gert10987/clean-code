/*
 * This file is generated by jOOQ.
 */
package com.easyprogramming.app.db.model;


import com.easyprogramming.app.db.model.tables.Orders;
import com.easyprogramming.app.db.model.tables.OrdersDescription;
import com.easyprogramming.app.db.model.tables.Products;
import com.easyprogramming.app.db.model.tables.records.OrdersDescriptionRecord;
import com.easyprogramming.app.db.model.tables.records.OrdersRecord;
import com.easyprogramming.app.db.model.tables.records.ProductsRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * scanner_app.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<OrdersRecord> ORDERS_PKEY = Internal.createUniqueKey(Orders.ORDERS, DSL.name("orders_pkey"), new TableField[] { Orders.ORDERS.ID }, true);
    public static final UniqueKey<OrdersDescriptionRecord> ORDERS_DESCRIPTION_PKEY = Internal.createUniqueKey(OrdersDescription.ORDERS_DESCRIPTION, DSL.name("orders_description_pkey"), new TableField[] { OrdersDescription.ORDERS_DESCRIPTION.ORDER_ID }, true);
    public static final UniqueKey<ProductsRecord> PRODUCTS_PKEY = Internal.createUniqueKey(Products.PRODUCTS, DSL.name("products_pkey"), new TableField[] { Products.PRODUCTS.ID }, true);
}
