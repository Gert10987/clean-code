package com.easyprogramming.scanner

import com.easyprogramming.App
import com.easyprogramming.catalog.exception.ProductNotExistException
import com.easyprogramming.orders.OrderId
import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@AutoConfigureMockMvc
@WebMvcTest(controllers = [ScannerAdapterRest.class])
@ContextConfiguration(classes = [App.class, TestConfig.class])
class ScannerIntegrationTests extends WebBaseIT {

    @Autowired
    private MockMvc mvc

    private ScannerAdapterRestFacade fakeClient = new FakeClient();

    def "Simple scanning products"() {
        given:
        def orderId = fakeClient.newOrder()

        when:
        fakeClient.scanProduct(orderId.getId(), "MILK")
        fakeClient.scanProduct(orderId.getId(), "LAPTOP")

        then:
        "1234_Money(amount=1228.22, currency=PLN)" == fakeClient.getDetails(orderId.getId())
    }

    def "Product not found should return 404"() {
        given:
        def orderId = fakeClient.newOrder()

        when:
        fakeClient.scanProduct(orderId.getId(), "BREAD")

        then:
        fakeClient.lastResult.response.status == 404

        thrown(ProductNotExistException.class)
    }

    private class FakeClient implements ScannerAdapterRestFacade {


        private MvcResult lastResult

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
            def res = mvc.perform(MockMvcRequestBuilders.patch("/orders/{id}", id)
                    .content(productType)
                    .contentType(MediaType.TEXT_PLAIN_VALUE))
                    .andReturn()

            lastResult = res

            if (res.response.status != 200) {
                throw res.getResolvedException()
            }
        }

        @Override
        String getDetails(UUID id) {
            mvc.perform(MockMvcRequestBuilders.get("/orders/{id}", id))
                    .andReturn()
                    .response
                    .contentAsString
        }
    }
}
