import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonRGBValidatorRouteTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("json-validator:classpath:schemas/rgbLightSchema.json")
                        .to("mock:result");
            }
        };
    }
    @EndpointInject("mock:result")
    MockEndpoint result;

    @Test
    public void testRGBSchemaOk() throws InterruptedException {
        String json = "{\"deviceType\":\"RGB\",\"label\":\"device1\",\"red\":255,\"green\":255,\"blue\":255,\"enabled\":true}";

        result.expectedMessageCount(1);

        template.sendBody("direct:start", json);

        result.assertIsSatisfied();
    }

    @Test
    public void testRGBSchemaMissingName() throws InterruptedException {
        String json = "{\"deviceType\":\"RGB\",\"status\":\"ON\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testRGBSchemaMissingStatus() throws InterruptedException {
        String json = "{\"deviceType\":\"RGB\",\"label\":\"device1\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testRGBSchemaMissingDeviceType() throws InterruptedException {
        String json = "{\"label\":\"device1\",\"status\":\"ON\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testRGBSchemaMissingAll() throws InterruptedException {
        String json = "{}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testRGBSchemaMissingRed() throws InterruptedException {
        String json = "{\"deviceType\":\"RGB\",\"label\":\"device1\",\"green\":255,\"blue\":255,\"enabled\":true}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testRGBSchemaMissingGreen() throws InterruptedException {
        String json = "{\"deviceType\":\"RGB\",\"label\":\"device1\",\"red\":255,\"blue\":255,\"enabled\":true}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testRGBSchemaMissingBlue() throws InterruptedException {
        String json = "{\"deviceType\":\"RGB\",\"label\":\"device1\",\"red\":255,\"green\":255,\"enabled\":true}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

    @Test
    public void testRGBSchemaExtraField() throws InterruptedException {
        String json = "{\"deviceType\":\"RGB\",\"label\":\"device1\",\"status\":\"ON\",\"extra\":\"extra\"}";

        assertThrows(org.apache.camel.CamelExecutionException.class, () -> template.sendBody("direct:start", json));
    }

}
