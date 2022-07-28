package scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shared.ProductType;

@RestController
@RequestMapping("/scanner")
public class ScannerAdapterRest implements InPorts.ScannerPort {

    private ScannerService scannerService;

    @Autowired
    public ScannerAdapterRest(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @Override
    @GetMapping("/new")
    public void newOrder() {
        scannerService.newOrder();
    }

    @Override
    @PutMapping("/scan")
    public void scanProduct(ProductType productType) {
        scannerService.scanProduct(productType);
    }
}
