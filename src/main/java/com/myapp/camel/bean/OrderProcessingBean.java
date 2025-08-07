package com.myapp.camel.bean;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.camel.language.xpath.XPath;

import java.util.Map;

public class OrderProcessingBean {
    public String[] recipients(@XPath("/order/@type") String order){
        if(freshOrders(order)){
            return new String[] {"activemq:queue:freshorders","activemq:queue:freshordersaudit"};
        }
        else{
            return new String[] {"activemq:queue:otherorders"};
        }
    }
    private boolean freshOrders(String orderType){
        return orderType.equals("fresh");
    }
    public String orderStatus(@Body Integer orderId, @Headers Map<String,String> headers){
        return "";
    }
}

// @XPath
// @Body Binds The Parameter to the message body

// @Header(name)
// @Headers

// Built In Types
// Exchange
// Message // Camel Input Message
// CamelContext
// TypeConverters
// Registry



// Camel Bean registries
// JndiRegistry
// SimpleRegistry
// ApplicationContextRegistry (ApplicationContext)
// Quarkus | Micronaut (CdiBeanRegistry)
