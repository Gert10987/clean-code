package catalog

import catalog.exception.ProductNotExistException
import shared.ProductType
import spock.lang.Specification

class CatalogServiceTest extends Specification {

    private CatalogService catalogService

    def setup() {
        catalogService = new CatalogService(Mock(InPorts.ProductsLoaderPort.class), new FakeOutPorts.TestCatalogDatabaseAdapter())
    }

    def 'should add milk to catalog'() {
        given:
        def milk = ProductTestData.prepareMilk()

        when:
        catalogService.add(milk)

        then:
        milk == catalogService.getByProductType(ProductType.MILK)
    }

    def 'should get product by id'() {
        given:
        def milk = ProductTestData.prepareMilk()
        def products = prepareContextWithTwoProducts(milk, ProductTestData.prepareLaptop())

        when:
        def res = catalogService.getById(products.get(ProductType.MILK).getId())

        then:
        res == milk
    }

    def 'should get product by type'() {
        given:
        def laptop = ProductTestData.prepareLaptop()
        prepareContextWithTwoProducts(ProductTestData.prepareMilk(), laptop)

        when:
        def res = catalogService.getByProductType(ProductType.LAPTOP)

        then:
        res == laptop
    }

    def 'should throw exception when product not found by type'() {

        when:
        catalogService.getByProductType(ProductType.LAPTOP)

        then:
        thrown(ProductNotExistException.class)
    }

    def 'should throw exception when product not found by id'() {

        when:
        catalogService.getById(new ProductId())

        then:
        thrown(ProductNotExistException.class)
    }

    private Map<ProductType, Product> prepareContextWithTwoProducts(Product milk, Product laptop) {
        catalogService.add(milk)
        catalogService.add(laptop)

        def res = [:]

        res[ProductType.MILK] = milk
        res[ProductType.LAPTOP] = laptop

        res as Map<ProductType, Product>
    }
}
