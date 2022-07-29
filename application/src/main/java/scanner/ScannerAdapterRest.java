package scanner;

import orders.OrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import shared.ProductType;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class ScannerAdapterRest {

    private ScannerService scannerService;

    @Autowired
    public ScannerAdapterRest(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @PostMapping()
    public OrderId newOrder() {
        return scannerService.newOrder();
    }

    @PatchMapping(value = "/{id}", consumes = {MediaType.TEXT_PLAIN_VALUE})
    public void scanProduct(@PathVariable UUID id, @RequestBody String productType) {
        scannerService.scanProduct(new OrderId(id), ProductType.valueOf(productType));
    }
}
