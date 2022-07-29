package scanner;

import orders.OrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shared.ProductType;

@RestController
@RequestMapping("/orders")
public class ScannerAdapterRest implements InPorts.ScannerPort {

    private ScannerService scannerService;

    @Autowired
    public ScannerAdapterRest(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @Override
    @PostMapping()
    public void newOrder() {
        scannerService.newOrder();
    }

    @Override
    @PatchMapping()
    public void scanProduct(OrderId id, ProductType productType) {
        scannerService.scanProduct(id, productType);
    }
}
