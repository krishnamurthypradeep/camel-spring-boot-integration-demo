package com.myapp.camel.routes;

import com.myapp.camel.avro.Order;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.avro.AvroDataFormat;
import org.springframework.stereotype.Component;

//@Component
public class AvroKafkaConsumer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //AvroDataFormat avroDataFormat = new AvroDataFormat(Order.SCHEMA$);
        from("kafka:order-avro"
                + "?groupId=order-avro-group"
                + "&valueDeserializer=io.confluent.kafka.serializers.KafkaAvroDeserializer"
                + "&schemaRegistryURL=http://localhost:8081"
                + "&autoOffsetReset=earliest")

                .process(exchange -> {
                    Object body = exchange.getIn().getBody();
                    if (body instanceof Order order) {
                        System.out.println("Received Order: " + order);
                    } else {
                        System.out.println("Received non-Order message: " + body);
                    }
                })
                .log("Received Avro Order: ${body}");
    }
}
