package catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
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
