package scanner;

import orders.DiscountFactory;
import shared.ProductType;

interface InPorts {

    interface DiscountLoaderPort {

        DiscountFactory loadDiscountData();
    }

    interface ScannerPort {

        void newOrder();

        void scanProduct(ProductType productType);
    }
}
