import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonDoorValidatorRouteTest extends CamelTestSupport {
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("json-validator:classpath:schemas/doorSchema.json")
                        .to("mock:result");
            }
        };
    }
    @EndpointInject("mock:result")
    MockEndpoint result;

    @Test
    public void testDoorSchemaOk() throws InterruptedException {
        String json = "{\"deviceType\":\"door\",\"label\":\"device1\",\"status\":\"ON\",\"enabled\":true}";

        result.expectedMessageCount(1);

        template.sendBody("direct:start", json);

        result.assertIsSatisfied();
    }

    @Test
    public void testDoorSchemaMissingName() throws InterruptedException {
        String json = "{\"deviceType\":\"door\",\"status\":\"ON\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testDoorSchemaMissingStatus() throws InterruptedException {
        String json = "{\"deviceType\":\"door\",\"label\":\"device1\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testDoorSchemaMissingDeviceType() throws InterruptedException {
        String json = "{\"label\":\"device1\",\"status\":\"ON\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testDoorSchemaMissingAll() throws InterruptedException {
        String json = "{}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testDoorSchemaExtraField() throws InterruptedException {
        String json = "{\"deviceType\":\"door\",\"label\":\"device1\",\"status\":\"ON\",\"extra\":\"extra\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }
}
