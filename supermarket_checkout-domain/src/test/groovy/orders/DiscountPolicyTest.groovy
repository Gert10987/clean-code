package orders

import catalog.ProductTestData
import shared.Money
import spock.lang.Specification

class DiscountPolicyTest extends Specification {

    def 'should calculate some user stories - Second50PercentCheaperForThreeDiscountPolicy'() {
        given:
        def policy = new Second50PercentCheaperForThreeDiscountPolicy()

        when:
        def totalDiscount = policy.calculateAndApply(product, items)

        then:
        expectedTotalDiscount == totalDiscount

        where:
        price   | product                            | items                                                              | expectedTotalDiscount
        "10.50" | ProductTestData.prepareMilk(price) | [ItemTestData.prepareMilk(price), ItemTestData.prepareMilk(price)] | Money.PLN("5.25")
        "10.50" | ProductTestData.prepareMilk(price) | [ItemTestData.prepareMilk(price)]                                  | Money.PLN("0")
        "1.80"  | ProductTestData.prepareMilk(price) | [ItemTestData.prepareMilk(price),
                                                        ItemTestData.prepareMilk(price),
                                                        ItemTestData.prepareMilk(price),
                                                        ItemTestData.prepareMilk(price)]                                  | Money.PLN("0.9") // Discount is applied only once
    }

    def 'should calculate some user stories - FreeForSpecificAmountInOrderPolicy, exist 3 free 1'() {
        given:
        def policy = new FreeForSpecificAmountInOrderPolicy(3, 1)

        when:
        def totalDiscount = policy.calculateAndApply(product, items)

        then:
        expectedTotalDiscount == totalDiscount

        where:
        price    | product                              | items                                                                  | expectedTotalDiscount
        "100.50" | ProductTestData.prepareLaptop(price) | [ItemTestData.prepareLaptop(price), ItemTestData.prepareLaptop(price)] | Money.PLN("0")
        "100.50" | ProductTestData.prepareLaptop(price) | [ItemTestData.prepareLaptop(price)]                                    | Money.PLN("0")
        "600"    | ProductTestData.prepareLaptop(price) | [ItemTestData.prepareLaptop(price),
                                                           ItemTestData.prepareLaptop(price),
                                                           ItemTestData.prepareLaptop(price),
                                                           ItemTestData.prepareLaptop(price)]                                    | Money.PLN("600")
        "600"    | ProductTestData.prepareLaptop(price) | [ItemTestData.prepareLaptop(price),
                                                           ItemTestData.prepareLaptop(price),
                                                           ItemTestData.prepareLaptop(price),
                                                           ItemTestData.prepareLaptop(price),
                                                           ItemTestData.prepareLaptop(price),
                                                           ItemTestData.prepareLaptop(price)]                                    | Money.PLN("600") // Discount is applied only once
    }
}
