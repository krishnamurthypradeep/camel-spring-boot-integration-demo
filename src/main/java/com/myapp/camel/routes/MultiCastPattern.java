package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MultiCastPattern extends RouteBuilder {
    public void configure() throws Exception {
        from("activemq:queue:orders?concurrentConsumers=10")
                .routeId("message-filter-pattern")
                .split().xpath("/orders/lineItems/product")
                .log(" Split Body:\n ${bodyAs(String)}")
                .filter().xpath("/product/type='HighPriority'")
                .multicast().parallelProcessing()
                .to("activemq:queue:accounting","activemq:queue:production")
                .end();

 from("activemq:queue:accounting")
         .bean("");
 // Best Practices
        // Camel Routes on routing,mediation and flow orchestration
        // Delegate Business Logic  and enrichment to beans
        // Separate bean class complex logic ,business rules
        // Testable, clean, maintain


        // camel use thread pool is used to manage threads

    }
}

// CBR
// Message Filter
// MultiCasting

// Aggregator
// Splitter

// RoutingSlip


// Load Balancer
