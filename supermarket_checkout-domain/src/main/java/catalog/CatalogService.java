package catalog;

import shared.ProductType;

public interface CatalogService {

    void add(Product product);

    Product getById(ProductId productId);

    Product getByProductType(ProductType productType);
}
