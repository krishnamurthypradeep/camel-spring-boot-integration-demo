package com.myapp.camel.bean;

import com.myapp.camel.routes.OrderItem;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component("orderAggregator")
public class OrderAggregator implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

       OrderItem newItem = newExchange.getIn().getBody(OrderItem.class);

       if(newItem == null) {
           throw new IllegalStateException("invalid order item");
       }
       OrderLineItems aggregated;
       if(oldExchange == null){
          aggregated = new OrderLineItems(newItem.orderId());
          aggregated.addItem(newItem);
          newExchange.getMessage().setBody(aggregated);
          return newExchange;
       } else {
           //
           aggregated = oldExchange.getMessage().getBody(OrderLineItems.class);
           if(aggregated == null){
               aggregated = new OrderLineItems(newItem.orderId());
           }
           aggregated.addItem(newItem);
           oldExchange.getMessage().setBody(aggregated);

       }
        return oldExchange;
    }
}

// {"orderId":3}
// {"orderId":3}
// {"orderId":3}