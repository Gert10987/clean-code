package com.easyprogramming.catalog

import com.easyprogramming.shared.ProductType

class FakeOutPorts {

    static class TestCatalogDatabaseAdapter implements OutPorts.CatalogDatabasePort {

        private Map<ProductId, Product> products = [:]

        @Override
        void add(Product product) {
            products.put(product.getId(), product)
        }

        @Override
        Optional<Product> getById(ProductId productId) {
            return Optional.ofNullable(products.get(productId))
        }

        @Override
        Optional<Product> getByProductType(ProductType productType) {
            return Optional.ofNullable(products
                    .values()
                    .find {it.getProductType() == productType})
        }
    }
}
