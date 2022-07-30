package com.easyprogramming.scanner

import com.easyprogramming.catalog.CatalogService
import com.easyprogramming.catalog.FakeOutPorts
import com.easyprogramming.catalog.ProductTestData
import com.easyprogramming.catalog.exception.ProductNotExistException
import com.easyprogramming.orders.OrderId
import org.junit.jupiter.api.Assertions
import com.easyprogramming.scanner.exception.OrderNotFoundException
import com.easyprogramming.shared.Money
import com.easyprogramming.shared.ProductType
import spock.lang.Shared
import spock.lang.Specification

class ScannerServiceTest extends Specification {

    @Shared
    private ScannerService scannerService

    @Shared
    private CatalogService catalogService

    def setup() {
        catalogService = new CatalogService(null, new FakeOutPorts.TestCatalogDatabaseAdapter())
        scannerService = new ScannerService(new com.easyprogramming.scanner.FakeOutPorts.TestOrdersDatabaseAdapter(), Mock(OutPorts.PrinterPort), catalogService, Mock(InPorts.DiscountLoaderPort))
    }

    def 'should calculate total value of order'() {
        given:
        def orderId = scannerService.newOrder()

        and:
        addProductToCatalog("10.11", ProductType.MILK)
        addProductToCatalog("1200", ProductType.LAPTOP)

        when:
        scanProduct(orderId, ProductType.MILK, 1)
        scanProduct(orderId, ProductType.LAPTOP, 1)

        then:
        Assertions.assertEquals(Money.PLN("1210.11"), scannerService.getTotalValueOfOrder(orderId))
    }

    def 'should throw order not found exception when order is not known'() {
        given:
        addProductToCatalog("10.11", ProductType.MILK)

        when:
        scanProduct(new OrderId(), ProductType.MILK, 1)

        then:
        thrown(OrderNotFoundException.class)
    }

    def 'should throw product not found exception when product not exist in catalog'() {
        given:
        def orderId = scannerService.newOrder()

        when:
        scanProduct(orderId, ProductType.MILK, 1)

        then:
        thrown(ProductNotExistException.class)
    }

    private void scanProduct(OrderId id, ProductType productType, int amount) {
        for (int i = 0; i < amount; i++) {
            scannerService.scanProduct(id, productType)
        }
    }

    private void addProductToCatalog(String price, ProductType productType) {
        catalogService.add(ProductTestData.sampleProduct(price: price, productType: productType))
    }
}
