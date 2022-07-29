package scanner;

import orders.DiscountFactory;
import orders.OrderId;
import shared.ProductType;

interface InPorts {

    interface DiscountLoaderPort {

        DiscountFactory loadDiscountData();
    }

    interface ScannerPort {

        void newOrder();

        void scanProduct(OrderId id, ProductType productType);
    }
}
