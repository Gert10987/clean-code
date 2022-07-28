package catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig {

    @Bean
    OutPorts.CatalogDatabasePort catalogDatabasePort() {
        return new CatalogDatabaseInAdapterJooq();
    }

    @Bean
    CatalogService catalogService(OutPorts.CatalogDatabasePort catalogDatabasePort) {
        return new CatalogService(null, catalogDatabasePort);
    }
}
