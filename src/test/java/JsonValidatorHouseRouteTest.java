import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonValidatorHouseRouteTest extends CamelTestSupport {


    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("json-validator:houseschema.json")
                        .to("mock:result");
            }
        };
    }

    @Test
    public void houseSchemaOkTest() throws InterruptedException {
        String json = "{\"name\":\"house1\",\"address\":\"http://localhost:8080\"}";

        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);

        template.sendBody("direct:start", json);

        mock.assertIsSatisfied();
    }

    @Test
    public void houseSchemaMissingAddressTest() throws InterruptedException {
        String json = "{\"name\":\"house1\"}";

        assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void houseSchemaMissingNameTest() throws InterruptedException {
        // Given
        String json = "{\"address\":\"http://localhost:8080\"}";

        assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }
}
