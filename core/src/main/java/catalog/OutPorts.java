package catalog;

import shared.ProductType;

import java.util.Optional;

interface OutPorts {

    interface CatalogDatabasePort {

        void add(Product product);

        Optional<Product> getById(ProductId productId);

        Optional<Product> getByProductType(ProductType productType);
    }
}
