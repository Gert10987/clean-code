package scanner;

import catalog.CatalogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
public class ScannerConfig {

    @Bean
    ScannerService scannerService(CatalogService catalogService) {
        return new ScannerService(null, catalogService, () -> null);
    }
}
