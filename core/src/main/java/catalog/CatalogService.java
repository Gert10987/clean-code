package catalog;

import catalog.exception.ProductNotExistException;
import shared.ProductType;

public class CatalogService {

    private final InPorts.ProductsLoaderPort productsLoaderPort;
    private final OutPorts.CatalogDatabasePort catalogDatabasePort;

    public CatalogService(InPorts.ProductsLoaderPort productsLoaderPort,
                          OutPorts.CatalogDatabasePort catalogDatabasePort) {
        this.productsLoaderPort = productsLoaderPort;
        this.catalogDatabasePort = catalogDatabasePort;
    }

    public void add(Product product) {
        catalogDatabasePort.add(product);
    }

    public Product getById(ProductId productId) {
        return catalogDatabasePort.getById(productId).orElseThrow(
                () -> new ProductNotExistException("Product not exist [%s]", productId));
    }

    public Product getByProductType(ProductType productType) {
        return catalogDatabasePort.getByProductType(productType).orElseThrow(
                () -> new ProductNotExistException("Product type not exist [%s]", productType));
    }
}
