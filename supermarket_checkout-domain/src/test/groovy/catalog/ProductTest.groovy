package catalog

import catalog.exception.ExpirationDateShouldBeAfterNowException
import spock.lang.Specification

import java.time.LocalDate

class ProductTest extends Specification {

    def 'should throw exception when the expiration date is before now'() {

        when:
        ProductTestData.sampleProduct(expirationDate: expirationDate)

        then:
        thrown(ExpirationDateShouldBeAfterNowException.class)

        where:
        expirationDate                | _
        LocalDate.now().minusDays(30) | _
        LocalDate.now().minusDays(1)  | _
    }

    def 'should not throw exception when the expiration date is equal to now or later'() {

        when:
        ProductTestData.sampleProduct(expirationDate: expirationDate)

        then:
        noExceptionThrown()

        where:
        expirationDate               | _
        LocalDate.now().plusDays(30) | _
        LocalDate.now().plusDays(1)  | _
        LocalDate.now()              | _
    }
}
