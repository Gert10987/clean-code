package com.easyprogramming.scanner;

import com.easyprogramming.orders.DiscountFactory;
import com.easyprogramming.orders.OrderId;
import com.easyprogramming.shared.ProductType;

interface InPorts {

    interface DiscountLoaderPort {

        DiscountFactory loadDiscountData();
    }

    interface ScannerPort {

        void newOrder();

        void scanProduct(OrderId id, ProductType productType);
    }
}
