package com.myapp.camel.routes;

import com.myapp.camel.avro.Order;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.avro.AvroDataFormat;

//@Component
public class AvroKafkaConsumer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        AvroDataFormat avroDataFormat = new AvroDataFormat(Order.SCHEMA$);
        from("kafka:orders?brokers=localhost:19092&groupId=my-group&autoOffsetReset=earliest")
                .unmarshal(avroDataFormat)
                .log("Received Order ${bodyAs(String)}");
    }
}
