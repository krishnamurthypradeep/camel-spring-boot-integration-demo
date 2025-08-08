package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RoutingSlipPattern extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:queue:newcombinedorders")
                .routeId("routingslip-route")
                .setProperty("totalPrice",jsonpath("$.totalPrice"))
                .setProperty("orderId",jsonpath("$.orderId"))
                .unmarshal().json(JsonLibrary.Jackson)
                .log(" Items being processed ${body}")
                .split().jsonpath("$.items[*]")
                .streaming()
                  .bean("orderItemConverter","mapToOrderItem")
                .log("Items being processed ${body}")
                .process("orderRoutingSlipBean")
                .marshal().json(JsonLibrary.Jackson)
                .log("Recipient List ${body}")
                .routingSlip(header("orderRoutingSlip"))
                .end();

    }
}
