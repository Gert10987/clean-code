package scanner;

import orders.OrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import shared.ProductType;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
class ScannerAdapterRest implements ScannerAdapterRestFacade {

    private ScannerService scannerService;

    @Autowired
    public ScannerAdapterRest(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @PostMapping
    public OrderId newOrder() {
        return scannerService.newOrder();
    }

    @PatchMapping(value = "/{id}", consumes = {MediaType.TEXT_PLAIN_VALUE})
    public void scanProduct(@PathVariable UUID id, @RequestBody String productType) {
        scannerService.scanProduct(new OrderId(id), ProductType.valueOf(productType));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public String getDetails(@PathVariable UUID id) {
        return scannerService.printBill(new OrderId(id));
    }
}
