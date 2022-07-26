package catalog

import shared.Money
import shared.ProductType

import java.time.LocalDate

class ProductTestData {

    static Product sampleProduct(Map<String, Object> properties = [:]) {

        properties = SAMPLE_MAP + properties

        new Product(new Money(properties.price as String, properties.currency as Currency) as Money, properties.productType as ProductType, properties.expirationDate as LocalDate)
    }

    static Product prepareMilk() {
        sampleProduct(productType: ProductType.MILK)
    }

    static Product prepareLaptop() {
        sampleProduct(productType: ProductType.LAPTOP)
    }

    static Map<String, Object> SAMPLE_MAP = [
            "price"         : "10.30",
            "currency"      : Currency.getInstance("PLN"),
            "productType"   : ProductType.LAPTOP,
            "expirationDate": LocalDate.now().plusDays(365)
    ]
}
