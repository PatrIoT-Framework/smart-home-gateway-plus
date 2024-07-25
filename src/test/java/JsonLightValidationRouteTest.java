import org.apache.camel.CamelExecutionException;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonLightValidationRouteTest extends CamelTestSupport {

    @EndpointInject("mock:result")
    MockEndpoint result;

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
    return new RouteBuilder() {
        @Override
        public void configure() throws Exception {
                from("direct:start")
                        .to("json-validator:classpath:schemas/lightSchema.json")
                        .to("mock:result");
            }
        };
    }

    @Test
    public void testDeviceSchemaOk() throws InterruptedException {
        String json = "{\"deviceType\":\"light\",\"label\":\"device1\",\"status\":\"on\",\"enabled\":true}";

        result.expectedMessageCount(1);

        template.sendBody("direct:start", json);

        result.assertIsSatisfied();
        assertTrue(result.getExchanges().get(0).getIn().getBody().toString().contains("deviceType"));
    }

    @Test
    public void testDeviceMinimalSchemaOK() {
        String json = "{\"deviceType\":\"light\",\"label\":\"device1\"}";

        template.sendBody("direct:start", json);

        assertTrue(result.getExchanges().get(0).getIn().getBody().toString().contains("deviceType"));
    }

    @Test
    public void testDeviceSchemaMissingName() throws InterruptedException {
        String json = "{\"deviceType\":\"light\"}";

        CamelExecutionException thrown = assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
        assertTrue(thrown.getCause() instanceof JsonValidationException);
    }

    @Test
    public void testDeviceSchemaMissingDeviceType() throws InterruptedException {
        String json = "{\"label\":\"device1\"}";

        CamelExecutionException thrown = assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
        assertTrue(thrown.getCause() instanceof JsonValidationException);
    }

    @Test
    public void testDeviceSchemaMissingAll() throws InterruptedException {
        String json = "{}";

        CamelExecutionException thrown = assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
        assertTrue(thrown.getCause() instanceof JsonValidationException);
    }

    @Test
    public void testDeviceSchemaExtraField() throws InterruptedException {
        String json = "{\"deviceType\":\"light\",\"label\":\"device1\",\"status\":\"on\",\"enabled\":true,\"extra\":\"extra\"}";

        CamelExecutionException thrown = assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
        assertTrue(thrown.getCause() instanceof JsonValidationException);
    }

    @Test
    public void testLightSchemaWrongStatus() {
        String json = "{\"deviceType\":\"light\",\"label\":\"device1\",\"status\":\"wrong\",\"enabled\":true}";

        CamelExecutionException thrown = assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
        assertTrue(thrown.getCause() instanceof JsonValidationException);
    }

    @Test
    public void testLightSchemaWrongStatusType() {
        String json = "{\"deviceType\":\"light\",\"label\":\"device1\",\"status\":true,\"enabled\":true}";

        CamelExecutionException thrown = assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
        assertTrue(thrown.getCause() instanceof JsonValidationException);
    }

    @Test
    public void testLightSchemaWrongEnabledType() {
        String json = "{\"deviceType\":\"light\",\"label\":\"device1\",\"status\":\"on\",\"enabled\":\"true\"}";

        CamelExecutionException thrown = assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
        assertTrue(thrown.getCause() instanceof JsonValidationException);
    }

    @Test
    public void testLightSchemaWrongLabelType() {
        String json = "{\"deviceType\":\"light\",\"label\":true,\"status\":\"on\",\"enabled\":true}";

        CamelExecutionException thrown = assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
        assertTrue(thrown.getCause() instanceof JsonValidationException);
    }

    @Test
    public void testLightSchemaWrongDeviceTypeType() {
        String json = "{\"deviceType\":true,\"label\":\"device1\",\"status\":\"on\",\"enabled\":true}";

        CamelExecutionException thrown = assertThrows(CamelExecutionException.class, () -> template.sendBody("direct:start", json));
        assertTrue(thrown.getCause() instanceof JsonValidationException);
    }
}
