package scanner

import catalog.CatalogService
import catalog.FakeOutPorts
import catalog.ProductTestData
import catalog.exception.ProductNotExistException
import org.junit.jupiter.api.Assertions
import shared.Money
import shared.ProductType
import spock.lang.Specification

class ScannerServiceTest extends Specification {

    private ScannerService scannerService
    private CatalogService catalogService

    def setup() {
        catalogService = new CatalogService(Mock(catalog.InPorts.ProductsLoaderPort.class), new FakeOutPorts.TestCatalogDatabaseAdapter())
        scannerService = new ScannerService(Mock(InPorts.ScannerPort), Mock(OutPorts.PrinterPort), catalogService, Mock(InPorts.DiscountLoaderPort))

        scannerService.newOrder()
    }

    def 'should calculate total value of order'() {
        given:
        addProductToCatalog("10.11", ProductType.MILK)
        addProductToCatalog("1200", ProductType.LAPTOP)

        when:
        scanProduct(ProductType.MILK, 1)
        scanProduct(ProductType.LAPTOP, 1)

        then:
        Assertions.assertEquals(Money.PLN("1210.11"), scannerService.getTotalValueOfOrder())
    }

    def 'should throw not found exception when product not exist in catalog'() {
        when:
        scanProduct(ProductType.MILK, 1)

        then:
        thrown(ProductNotExistException.class)
    }

    private void scanProduct(ProductType productType, int amount) {
        for (int i = 0; i < amount; i++) {
            scannerService.scanProduct(productType)
        }
    }

    private void addProductToCatalog(String price, ProductType productType) {
        catalogService.add(ProductTestData.sampleProduct(price: price, productType: productType))
    }
}
