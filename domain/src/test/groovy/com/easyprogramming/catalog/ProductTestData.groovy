package com.easyprogramming.catalog

import com.easyprogramming.shared.Money
import com.easyprogramming.shared.ProductType

import java.time.LocalDate

class ProductTestData {

    static Product sampleProduct(Map<String, Object> properties = [:]) {

        properties = SAMPLE_MAP + properties

        new Product(new Money(properties.price as String, properties.currency as Currency) as Money, properties.productType as ProductType, properties.expirationDate as LocalDate)
    }

    static Product prepareMilk(String price = "3.00") {
        sampleProduct(productType: ProductType.MILK, price: price)
    }

    static Product prepareLaptop(String price = "1000.0") {
        sampleProduct(productType: ProductType.LAPTOP, price: price)
    }

    static Map<String, Object> SAMPLE_MAP = [
            "price"         : "10.30",
            "currency"      : Currency.getInstance("PLN"),
            "productType"   : ProductType.LAPTOP,
            "expirationDate": LocalDate.now().plusDays(365)
    ]
}
