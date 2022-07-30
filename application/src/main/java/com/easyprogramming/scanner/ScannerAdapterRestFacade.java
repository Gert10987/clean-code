package com.easyprogramming.scanner;

import com.easyprogramming.orders.OrderId;

import java.util.UUID;

/**
 * Port to communicate user with system
 */
public interface ScannerAdapterRestFacade {

    /**
     * Create new order
     * @return Order id
     */
    OrderId newOrder();

    /**
     * Scanning and calculating price
     * @param id order id
     * @param productType product type/name
     */
    void scanProduct(UUID id, String productType);

    /**
     * Get details of order
     * @param id order id
     * @return Bill content
     */
    String getDetails(UUID id);
}
