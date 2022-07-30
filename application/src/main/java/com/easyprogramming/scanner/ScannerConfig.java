package com.easyprogramming.scanner;

import com.easyprogramming.catalog.CatalogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
class ScannerConfig {

    @Bean
    OutPorts.PrinterPort printerPort() {
        return new PrinterOutAdapter();
    }
    @Bean
    OutPorts.OrdersDatabasePort ordersDatabasePort() {
        return new OrdersDatabaseOutAdapterJooq();
    }

    @Bean
    ScannerService scannerService(CatalogService catalogService, OutPorts.OrdersDatabasePort ordersDatabasePort, OutPorts.PrinterPort printerPort) {
        return new ScannerService(ordersDatabasePort, printerPort, catalogService, () -> null);
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
