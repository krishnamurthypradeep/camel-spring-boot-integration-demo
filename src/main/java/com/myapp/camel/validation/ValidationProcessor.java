package com.myapp.camel.validation;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component("validationProcessor")
public class ValidationProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
       String body =  exchange.getIn().getBody(String.class);
       if(body==null || body.isBlank()){
           throw new IllegalArgumentException("body is null or empty");
       }
    }
}
