package com.easyprogramming.scanner;

import com.easyprogramming.orders.OrderId;
import com.easyprogramming.shared.ProductType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
class ScannerAdapterRest implements ScannerAdapterRestFacade {

    private final Logger log = LoggerFactory.getLogger("scanner");

    private final ScannerService scannerService;

    @Autowired
    public ScannerAdapterRest(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @PostMapping
    public OrderId newOrder() {
        OrderId orderId = scannerService.newOrder();
        log.info("New order, [{}]", orderId);
        return orderId;
    }

    @PatchMapping(value = "/{id}", consumes = {MediaType.TEXT_PLAIN_VALUE})
    public void scanProduct(@PathVariable UUID id, @RequestBody String productType) {
        scannerService.scanProduct(new OrderId(id), ProductType.valueOf(productType));
        log.info("Scan with succeed, [{}], product: [{}]", id, productType);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public String getDetails(@PathVariable UUID id) {
        return scannerService.printBill(new OrderId(id));
    }
}
