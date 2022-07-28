package scanner;

import catalog.CatalogService;
import catalog.Product;
import orders.Order;
import scanner.exception.BillingIsNotPresentException;
import scanner.model.Billing;
import shared.Money;
import shared.ProductType;

public class ScannerService {
    private final OutPorts.PrinterPort printerPort;

    private final CatalogService catalogService;
    private final InPorts.DiscountLoaderPort discountLoaderPort;
    private Order tempOrder;

    public ScannerService(OutPorts.PrinterPort printerPort,
                          CatalogService catalogService, InPorts.DiscountLoaderPort discountLoaderPort) {
        this.printerPort = printerPort;
        this.catalogService = catalogService;
        this.discountLoaderPort = discountLoaderPort;
    }

    public void newOrder() {
        tempOrder = new Order(discountLoaderPort.loadDiscountData());
    }

    public void scanProduct(ProductType productType) {
        Product foundProduct = catalogService.getByProductType(productType);

        tempOrder.addProduct(foundProduct);
    }

    public Money getTotalValueOfOrder() {
        return tempOrder.getTotalPrice();
    }

    public String printBill() {
        return printerPort.getBill(tempOrder).map(Billing::getContent).orElseThrow(
                () -> new BillingIsNotPresentException("Billing is null"));
    }
}
