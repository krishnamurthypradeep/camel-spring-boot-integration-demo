package com.myapp.camel.routes;

import com.myapp.camel.bean.OrderProcessingBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RecipientListPatternJavaBean extends RouteBuilder {

    public void configure() throws Exception {
        from("file:data/orders?include=.*\\.(xml|json|csv)&noop=true&autoCreate=false&directoryMustExist=true")
                .routeId("contentBasedRouteDesignPattern and recipientList")

                .log("File ${header:CamelFileName}")
                .choice()
                .when(simple("${body[orderType]} == 'FRESH"))
                .setHeader("recipients",method(OrderProcessingBean.class,"recipients"))
                //.to("activemq:queue:xmlproducts")
                .when(simple("${body[orderType]} == 'Electronics"))
                .setHeader("recipients",constant("activemq:queue:electronicsorders,activemq:queue:electronicsOrdersAudit"))
                //.to("activemq:queue:jsonproducts")
                .otherwise()
                .setHeader("recipients",constant("activemq:queue:archive"))
                //.to("activemq:queue:otherproducts")

                .end().recipientList(header("recipients"));


    }
}
