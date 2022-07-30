package scanner

import app.App
import com.jayway.jsonpath.JsonPath
import orders.OrderId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.web.util.NestedServletException

@AutoConfigureMockMvc
@WebMvcTest(controllers = [ScannerAdapterRest.class])
@ContextConfiguration(classes = [App.class, TestConfig.class])
class ScannerIntegrationTests extends WebBaseITTest implements ScannerAdapterRestFacade {

    @Autowired
    private MockMvc mvc

    def "Simple scanning products"() {
        given:
        def orderId = newOrder()

        when:
        scanProduct(orderId.getId(), "MILK")
        scanProduct(orderId.getId(), "LAPTOP")

        then:
        "1234_Money(amount=1228.22, currency=PLN)" == getDetails(orderId.getId())
    }

    // TODO change code
    def "Not found should return 400"() {
        given:
        def orderId = newOrder()

        when:
        scanProduct(orderId.getId(), "UNKNOWN")

        then:
        thrown(NestedServletException.class)
    }

    @Override
    OrderId newOrder() {
        def response = mvc.perform(MockMvcRequestBuilders.post("/orders"))
                .andReturn()
                .response
                .contentAsString

        new OrderId(UUID.fromString(JsonPath.read(response, '$.id')))
    }

    @Override
    void scanProduct(UUID id, String productType) {
        mvc.perform(MockMvcRequestBuilders.patch("/orders/{id}", id)
                .content(productType)
                .contentType(MediaType.TEXT_PLAIN_VALUE))
    }

    @Override
    String getDetails(UUID id) {
        mvc.perform(MockMvcRequestBuilders.get("/orders/{id}", id))
                .andReturn()
                .response
                .contentAsString
    }
}
