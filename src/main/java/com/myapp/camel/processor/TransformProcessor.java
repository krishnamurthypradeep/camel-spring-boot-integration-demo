package com.myapp.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component("transformProcessor")
public class TransformProcessor  implements Processor {
    public void process(Exchange exchange) throws Exception {
        String enriched = exchange.getIn().getHeader("myData", String.class);
        String original = exchange.getIn().getBody(String.class);
        exchange.getMessage().setBody(original+" | transformed with "+enriched);


    }
}
