package com.easyprogramming.catalog;

import com.easyprogramming.catalog.exception.ProductNotExistException;
import com.easyprogramming.shared.ProductType;

/**
 * Managing products inside the shop
 */
public class CatalogService {

    private final InPorts.ProductsLoaderPort productsLoaderPort;
    private final OutPorts.CatalogDatabasePort catalogDatabasePort;

    public CatalogService(InPorts.ProductsLoaderPort productsLoaderPort,
                          OutPorts.CatalogDatabasePort catalogDatabasePort) {
        this.productsLoaderPort = productsLoaderPort;
        this.catalogDatabasePort = catalogDatabasePort;
    }

    /**
     * Create new product
     * @param product product definition
     */
    public void add(Product product) {
        catalogDatabasePort.add(product);
    }

    /**
     * Fetch product by id
     * @param productId unique product id
     * @return product definition
     */
    public Product getById(ProductId productId) {
        return catalogDatabasePort.getById(productId).orElseThrow(
                () -> new ProductNotExistException("Product not exist [%s]", productId));
    }

    /**
     * Fetch product by id
     * @param productType Name/type of product
     * @return product definition
     */
    public Product getByProductType(ProductType productType) {
        return catalogDatabasePort.getByProductType(productType)
                .orElseThrow(() -> new ProductNotExistException("Product type not exist [%s]", productType));
    }
}
