package scanner;

import catalog.CatalogService;
import catalog.Product;
import orders.Order;
import orders.OrderId;
import scanner.exception.BillingIsNotPresentException;
import scanner.exception.OrderNotFoundException;
import scanner.model.Billing;
import shared.Money;
import shared.ProductType;

public class ScannerService {
    private final OutPorts.OrdersDatabasePort orders;
    private final OutPorts.PrinterPort printerPort;

    private final CatalogService catalogService;
    private final InPorts.DiscountLoaderPort discountLoaderPort;

    public ScannerService(OutPorts.OrdersDatabasePort orders, OutPorts.PrinterPort printerPort,
                          CatalogService catalogService, InPorts.DiscountLoaderPort discountLoaderPort) {
        this.orders = orders;
        this.printerPort = printerPort;
        this.catalogService = catalogService;
        this.discountLoaderPort = discountLoaderPort;
    }

    public OrderId newOrder() {
        Order order = new Order(discountLoaderPort.loadDiscountData());
        orders.add(order);

        return order.getId();
    }

    public void scanProduct(OrderId id, ProductType productType) {
        Product foundProduct = catalogService.getByProductType(productType);

        orders.getById(id).ifPresentOrElse(order -> {
            order.addProduct(foundProduct);
            orders.update(order);
        }, () -> {
            throw new OrderNotFoundException("Order not found [%s]", id);
        });
    }

    public Money getTotalValueOfOrder(OrderId id) {
        return orders.getById(id)
                .map(Order::getActualTotalPrice)
                .orElseThrow(() -> new OrderNotFoundException("Order not found [%s]", id));
    }

    public String printBill(OrderId id) {
        Order order = orders.getById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found [%s]", id));

        return printerPort.getBill(order)
                .map(Billing::getContent)
                .orElseThrow(() -> new BillingIsNotPresentException("Billing is null"));
    }
}
