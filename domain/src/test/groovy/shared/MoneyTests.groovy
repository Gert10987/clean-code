package shared

import shared.exception.CalculatingMoneyException
import spock.lang.Specification

class MoneyTests extends Specification {

    def 'should add two money objects properly'() {

        when:
        Money res = Money.PLN(base).add(Money.PLN(toAdd))

        then:
        Money.PLN(expected) == res

        where:
        base      | toAdd   | expected
        "100"     | "10"    | "110"
        "0.92"    | "0.80"  | "1.72"
        "126.22"  | "88.88" | "215.10"
        "100"     | "100"   | "200"
        "7.88"    | "2.888" | "10.77"
        "7.88888" | "2.888" | "10.78"
    }

    def 'should subtract two money objects properly'() {

        when:
        Money res = Money.PLN(base) - Money.PLN(toSubtract)

        then:
        Money.PLN(expected) == res

        where:
        base     | toSubtract | expected
        "100"    | "10"       | "90"
        "0.92"   | "0.80"     | "0.12"
        "126.22" | "88.88"    | "37.34"
        "100"    | "100"      | "0"
    }

    def 'should throw exception when money is equal than zero'() {

        when:
        new Money("-1", Currency.getInstance("PLN"))

        then:
        thrown(CalculatingMoneyException.class)
    }

    def 'should throw exception when currency is not PLN'() {

        when:
        new Money("-1", Currency.getInstance(currency))

        then:
        thrown(CalculatingMoneyException.class)

        where:
        currency | _
        "EUR"    | _
        "USD"    | _
        "GBP"    | _
    }
}
