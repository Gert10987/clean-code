package scanner

import app.App
import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@AutoConfigureMockMvc
@WebMvcTest(controllers = [ScannerAdapterRest.class])
@ContextConfiguration(classes = [App.class, TestConfig.class])
class ScannerIntegrationTests extends WebBaseITTest {

    @Autowired
    private MockMvc mvc

    def "Simple scan products"() {
        when:
        def response = mvc.perform(MockMvcRequestBuilders.post("/orders"))
                .andReturn()
                .response
                .contentAsString

        def id = JsonPath.read(response, '$.id')

        and:
        mvc.perform(MockMvcRequestBuilders.patch("/orders/{id}", id)
                .content("MILK")
                .contentType(MediaType.TEXT_PLAIN_VALUE))

        mvc.perform(MockMvcRequestBuilders.patch("/orders/{id}", id)
                .content("LAPTOP")
                .contentType(MediaType.TEXT_PLAIN_VALUE));

        then:
        mvc.perform(MockMvcRequestBuilders.get("/orders/{id}", id))
                .andReturn()
                .response
                .contentAsString == "1234_Money(amount=2.00, currency=PLN)"
    }
}
