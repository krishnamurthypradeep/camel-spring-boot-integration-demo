package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
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

// CBR
// Message Filter
// MultiCasting

// Aggregating
// 1. Correlation Identifier : Expression Which Incoming messages
// 2. Completion Condition :  Predicate
// 3. Aggregation Strategy : Strategy on how to combine these messages
// 4. Order1, Order2, Order3 -> [Order1,Order2,Order3]


