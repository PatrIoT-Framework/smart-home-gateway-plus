import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonFireplaceValidatorRouteTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("json-validator:classpath:schemas/fireplaceSchema.json")
                        .to("mock:result");
            }
        };
    }
    @EndpointInject("mock:result")
    MockEndpoint result;

    @Test
    public void testDeviceSchemaOk() throws InterruptedException {
        String json = "{\"deviceType\":\"fireplace\",\"label\":\"device1\",\"status\":\"ON\"}";

        result.expectedMessageCount(1);

        template.sendBody("direct:start", json);

        result.assertIsSatisfied();
    }

    @Test
    public void testDeviceSchemaMissingName() throws InterruptedException {
        String json = "{\"deviceType\":\"fireplace\",\"status\":\"ON\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testDeviceSchemaMissingStatus() throws InterruptedException {
        String json = "{\"deviceType\":\"fireplace\",\"label\":\"device1\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testDeviceSchemaMissingDeviceType() throws InterruptedException {
        String json = "{\"label\":\"device1\",\"status\":\"ON\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testDeviceSchemaMissingAll() throws InterruptedException {
        String json = "{}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testDeviceSchemaExtraField() throws InterruptedException {
        String json = "{\"deviceType\":\"fireplace\",\"label\":\"device1\",\"status\":\"ON\",\"extra\":\"extra\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }




}
