package com.myapp.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ContentBasedRouteDesignPattern extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:data/orders?include=.*\\.(xml|json|csv)&noop=true&autoCreate=false&directoryMustExist=true")
                .routeId("contentBasedRouteDesignPattern")
                .setHeader("fileType",simple("${file:ext}"))
                .log("File ${header:CamelFileName}")
                .choice()
                .when(header("fileType").isEqualTo("xml"))
                //.to("activemq:queue:xmlproducts")
                .when(header("fileType").isEqualTo("json"))
                //.to("activemq:queue:jsonproducts")
                .otherwise()
                //.to("activemq:queue:otherproducts")
                .toD("activemq:queue:${header.fileType}")
                .end();

    }
}
// CBR (choice /when/otherwise)
// Route To Different Destinations

// Message Filter
// filter
// Pass Or Drop Messages

