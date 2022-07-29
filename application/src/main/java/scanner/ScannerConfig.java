package scanner;

import catalog.CatalogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
public class ScannerConfig {

    @Bean
    OutPorts.OrdersDatabasePort ordersDatabasePort() {
        return new OrdersDatabaseInAdapterJooq();
    }

    @Bean
    ScannerService scannerService(CatalogService catalogService, OutPorts.OrdersDatabasePort ordersDatabasePort) {
        return new ScannerService(ordersDatabasePort, null, catalogService, () -> null);
    }
}
