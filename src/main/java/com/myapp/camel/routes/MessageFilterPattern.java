package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageFilterPattern extends RouteBuilder {
    public void configure() throws Exception {
        from("activemq:queue:orders")
                .routeId("message-filter-pattern")
                .split().xpath("/orders/lineItems/product")
                .log(" Split Body:\n ${bodyAs(String)}")
                .filter().xpath("/product/type='HighPriority'")
                .to("activemq:queue:highpriorityorders")
                .end();

    }
}
