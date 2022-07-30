package scanner;

import catalog.CatalogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
public class ScannerConfig {

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
}
