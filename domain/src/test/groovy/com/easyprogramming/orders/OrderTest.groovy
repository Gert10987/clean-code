package com.easyprogramming.orders

import com.easyprogramming.catalog.ProductTestData
import com.easyprogramming.shared.Money
import com.easyprogramming.shared.ProductType
import spock.lang.Specification

class OrderTest extends Specification {

    private Order order

    def 'should add one product to order and calculate total price'() {
        given:
        order = new Order(null)

        when:
        order.addProduct(ProductTestData.prepareMilk("10.30"))

        then:
        order.getActualTotalPrice() == Money.PLN("10.30")
    }

    def 'should some products to order and calculate total price'() {
        given:
        order = new Order(null)

        and: "expected value"
        Money expectedTotalPrice = Money.PLN("231.20")

        when:
        order.addProduct(ProductTestData.prepareMilk("10.30"))
        order.addProduct(ProductTestData.prepareMilk("10.30"))
        order.addProduct(ProductTestData.prepareMilk("10.30"))
        order.addProduct(ProductTestData.prepareLaptop("200.30"))

        then:
        order.getActualTotalPrice() == expectedTotalPrice
    }

    def 'should add discount to products - two for three'() {
        given:
        DiscountFactory discountFactory = Mock(DiscountFactory)
        order = new Order(discountFactory)

        and: "mock discount"
        discountFactory.getByProductType(ProductType.MILK) >> [new FreeForSpecificAmountInOrderPolicy(3, 1)]

        and: "expected value"
        Money expectedTotalPrice = Money.PLN("20.60")

        when:
        order.addProduct(ProductTestData.prepareMilk("10.30"))
        order.addProduct(ProductTestData.prepareMilk("10.30"))
        order.addProduct(ProductTestData.prepareMilk("10.30"))

        then:
        order.getActualTotalPrice() == expectedTotalPrice
    }

    def 'should add discount to products - second 50% cheaper'() {
        given:
        DiscountFactory discountFactory = Mock(DiscountFactory)
        order = new Order(discountFactory)

        and: "mock discount"
        discountFactory.getByProductType(ProductType.MILK) >> [new Second50PercentCheaperPolicy()]

        and: "expected value"
        Money expectedTotalPrice = Money.PLN("15.45")

        when:
        order.addProduct(ProductTestData.prepareMilk("10.30"))
        order.addProduct(ProductTestData.prepareMilk("10.30"))

        then:
        order.getActualTotalPrice() == expectedTotalPrice
    }

    def 'should add discount to products - second 50% cheaper + one for two'() {
        given:
        DiscountFactory discountFactory = Mock(DiscountFactory)
        order = new Order(discountFactory)

        and: "mock discount"
        discountFactory.getByProductType(ProductType.MILK) >> [new Second50PercentCheaperPolicy()]
        discountFactory.getByProductType(ProductType.LAPTOP) >> [new FreeForSpecificAmountInOrderPolicy(2, 1)]

        and: "expected value"
        Money expectedTotalPrice = Money.PLN("1015.45")

        when:
        order.addProduct(ProductTestData.prepareMilk("10.30"))
        order.addProduct(ProductTestData.prepareMilk("10.30"))
        order.addProduct(ProductTestData.prepareLaptop("1000"))
        order.addProduct(ProductTestData.prepareLaptop("1000"))

        then:
        order.getActualTotalPrice() == expectedTotalPrice
    }
}
