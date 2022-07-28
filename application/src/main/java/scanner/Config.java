package scanner;

import catalog.CatalogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    ScannerService scannerService(CatalogService catalogService) {
        return new ScannerService(null, catalogService, () -> null);
    }
}
