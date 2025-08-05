package com.myapp.camel.routes;

import com.myapp.camel.bean.Product;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.stereotype.Component;

@Component
public class FileToActiveMQUsingJAXB extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat();
        jaxbDataFormat.setContextPath(Product.class.getPackageName());

        from("file:data/xml?include=products-.*.xml&noop=true&autoCreate=false&directoryMustExist=true")
                .unmarshal(jaxbDataFormat)
                .marshal().json().
                to("activemq:queue:products");
        // ActiveMQ Component
        // JMS producer
    }
}
