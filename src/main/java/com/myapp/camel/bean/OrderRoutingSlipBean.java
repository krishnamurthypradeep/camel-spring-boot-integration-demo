package com.myapp.camel.bean;

import com.myapp.camel.routes.OrderItem;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("orderRoutingSlipBean")
public class OrderRoutingSlipBean implements Processor {

@Autowired
private JdbcTemplate jdbcTemplate;

    public void process(Exchange exchange) throws Exception {
     OrderItem orderItem =   exchange.getIn().getBody(OrderItem.class);
    String productName = orderItem.productName();

    String sql = "SELECT route_chain FROM order_routes WHERE product_name=?";
   List<String> results = jdbcTemplate.queryForList(sql, new Object[]{productName},String.class);
        String route = results.isEmpty() ? null : results.get(0);
   if(route == null) {
        route ="activemq:queue:otherorders";
    }
    exchange.getIn().setHeader("orderRoutingSlip", route);
    }
}


// Error Handling Pattern

// a. Idempotent Filter Pattern

// Intent -> Filter Out duplicate messages, Idempotent Reciever EIP

// b. Transaction Error handling
// c. Retry Pattern
// d. Circuit Breaker Pattern
// e. Error Channel Pattern
