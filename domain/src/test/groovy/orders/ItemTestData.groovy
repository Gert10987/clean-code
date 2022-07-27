package orders

import shared.Money
import shared.ProductType

class ItemTestData {

    static Item sampleItem(Map<String, Object> properties = [:]) {

        properties = SAMPLE_MAP + properties

        new Item(properties.productType as ProductType, new Money(properties.price as String, properties.currency as Currency))
    }

    static Item prepareMilk(String price = "3.00") {
        sampleItem(productType: ProductType.MILK, price: price)
    }

    static Item prepareLaptop(String price = "1000.0") {
        sampleItem(productType: ProductType.LAPTOP, price: price)
    }

    static Map<String, Object> SAMPLE_MAP = [
            "price"         : "10.30",
            "currency"      : Currency.getInstance("PLN")
    ]
}
