import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonThermometerValidatorRouteTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("json-validator:classpath:schemas/thermometerSchema.json")
                        .to("mock:result");
            }
        };
    }
    @EndpointInject("mock:result")
    MockEndpoint result;

    @Test
    public void testThermometerSchemaOk() throws InterruptedException {
        String json = "{\"deviceType\":\"thermometer\",\"label\":\"device1\",\"temperature\":25.0,\"enabled\":true,\"unit\":\"C\"}";

        result.expectedMessageCount(1);

        template.sendBody("direct:start", json);

        result.assertIsSatisfied();
    }

    @Test
    public void testThermometerSchemaMissingName() throws InterruptedException {
        String json = "{\"deviceType\":\"thermometer\",\"temperature\":25.0}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testThermometerSchemaMissingDeviceType() throws InterruptedException {
        String json = "{\"label\":\"device1\",\"temperature\":25.0,\"enabled\":true,\"unit\":\"C\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testThermometerSchemaMissingUnit() throws InterruptedException {
        String json = "{\"deviceType\":\"thermometer\",\"label\":\"device1\",\"temperature\":25.0,\"enabled\":true}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }
    @Test
    public void testThermometerSchemaMissingEnabled() throws InterruptedException {
        String json = "{\"deviceType\":\"thermometer\",\"label\":\"device1\",\"temperature\":25.0,\"unit\":\"C\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }
    
    @Test
    public void testThermometerSchemaMissingAll() throws InterruptedException {
        String json = "{}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testThermometerSchemaExtraField() throws InterruptedException {
        String json = "{\"deviceType\":\"thermometer\",\"label\":\"device1\",\"temperature\":25.0,\"enabled\":true,\"unit\":\"C\",\"extraField\":true}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }
}
