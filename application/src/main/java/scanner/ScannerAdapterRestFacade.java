package scanner;

import orders.OrderId;

import java.util.UUID;

public interface ScannerAdapterRestFacade {

    OrderId newOrder();

    void scanProduct(UUID id, String productType);

    String getDetails(UUID id);
}
