package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class WireTapPattern extends RouteBuilder {
    public void configure() throws Exception {
        from("activemq:queue:orders")
                .routeId("message-filter-pattern")
//                .process(exchange -> {
//                    exchange.getIn().setBody(exchange.getIn().getBody(String.class));
//                })
                .wireTap("activemq:queue:ordersAudit")
                .split().xpath("/orders/lineItems/product")
                .log(" Split Body:\n ${bodyAs(String)}")
                .filter().xpath("/product/type='HighPriority'")
                .multicast().parallelProcessing()
                .to("activemq:queue:accounting","activemq:queue:production")
                .end();
        // camel use thread pool is used to manage threads

    }
}

// CBR
// Message Filter
// MultiCasting
