package catalog;

import shared.ProductType;

import java.util.Optional;

public class CatalogDatabaseInAdapterJooq implements OutPorts.CatalogDatabasePort {
    @Override
    public void add(Product product) {

    }

    @Override
    public Optional<Product> getById(ProductId productId) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getByProductType(ProductType productType) {
        return Optional.empty();
    }
}
