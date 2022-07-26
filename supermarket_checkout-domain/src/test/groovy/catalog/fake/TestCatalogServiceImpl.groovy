package catalog.fake

import catalog.CatalogService
import catalog.Product
import catalog.ProductId
import catalog.exception.ProductNotExistException
import shared.ProductType

class TestCatalogServiceImpl implements CatalogService {

    private Map<ProductId, Product> products = [:]

    @Override
    void add(Product product) {
        products.put(product.getId(), product)
    }

    @Override
    Product getById(ProductId productId) {
        def product = products.get(productId)

        if (product == null) {
            throw new ProductNotExistException("Product not exist [%s]", productId)
        }

        return product
    }

    @Override
    Product getByProductType(ProductType productType) {
        return Optional.ofNullable(products
                .values()
                .find {it.getProductType() == productType})
                .orElseThrow(() -> new ProductNotExistException("Product type not exist [%s]", productType))
    }
}
