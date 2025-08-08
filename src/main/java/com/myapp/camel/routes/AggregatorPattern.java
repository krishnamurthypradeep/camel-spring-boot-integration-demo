package com.myapp.camel.routes;

import com.myapp.camel.bean.OrderAggregator;
import com.myapp.camel.bean.OrderLineItems;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.leveldb.LevelDBAggregationRepository;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.AggregationRepository;
import org.springframework.stereotype.Component;

// Order1,Order2,Order3,Order4
@Component
public class AggregatorPattern extends RouteBuilder {
    public void configure() throws Exception {

        AggregationRepository repository = new LevelDBAggregationRepository("orderrepo","repo/orderrepo.dat");
        from("activemq:queue:neworders")
                .routeId("aggregator-route")
                .unmarshal().json(JsonLibrary.Jackson, OrderItem.class)
                // correlation Identifier OrderId
                .setHeader("orderId", simple("${body.orderId}"))
                .aggregate(header("orderId"),new OrderAggregator())
                .completionSize(3)
                .completionTimeoutCheckerInterval(1000)
                .aggregationRepository(repository)
                .marshal().json(JsonLibrary.Jackson, OrderLineItems.class)
                .convertBodyTo(String.class)
                .to("activemq:queue:newcombinedorders");


    }
}

