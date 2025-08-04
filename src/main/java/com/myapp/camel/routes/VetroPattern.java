package com.myapp.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class VetroPattern extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:data/input?noop=true&recursive=true&initialDelay=5000&delay=2000&include=.*\\.txt$")
                .routeId("vetro-route-1")
                // validation
                .process("validationProcessor")
                .to("direct:enrich")
                .process("transformProcessor")
                // Content Based Router
                // Simple Expression, XPath
                        .choice()
                                .when(simple("${body} contains 'mydata'"))
                                        .to("file:data/output1")
                                                .otherwise()
                .log(LoggingLevel.INFO, "${body} contains 'mydata'")
                        .to("file:data/output2")
                                .end();


        from("direct:enrich")
        .routeId("vetro-route-2")
                .process("enrichProcessor");


    }
}

// Validate
// Enrich
// Transform (XSLT, String Template, Chunk , Velocity, XQuery)
// Route
// Operate
