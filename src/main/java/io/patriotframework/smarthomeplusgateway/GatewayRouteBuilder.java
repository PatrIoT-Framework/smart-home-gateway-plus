package io.patriotframework.smarthomeplusgateway;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * GatewayRouteBuilder is a class that extends RouteBuilder and is used to define the routes of the gateway application.
 */
@Component
public class GatewayRouteBuilder extends RouteBuilder {

        @Override
        public void configure() throws Exception {

            restConfiguration()
                    .component("servlet")
                    .bindingMode("json");

            rest("/api/v0.1/gateway/")
                    .get("/rgblight/{label}")
                    .to("direct:rgblight");

            from("direct:rgblight")
                    .log(LoggingLevel.INFO,"RGB Light with label ${header.label} is being called")
                    .transform().simple("Hello from RGB Light with label ${header.label}")};
}
