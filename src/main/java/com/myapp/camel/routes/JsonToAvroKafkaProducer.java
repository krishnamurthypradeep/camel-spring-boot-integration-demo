package com.myapp.camel.routes;

import com.myapp.camel.avro.Order;
import org.apache.avro.Schema;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.dataformat.avro.AvroDataFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonToAvroKafkaProducer extends RouteBuilder {

    @Autowired
    private JacksonDataFormat jacksonDataFormatForOrder;
    @Override
    public void configure() throws Exception {

      AvroDataFormat avroDataFormat = new AvroDataFormat(Order.SCHEMA$);
        from("file:data/avroorders?include=.*\\.json$&noop=true&autoCreate=false&directoryMustExist=true")
                .routeId("json-avro-route")
                .log("File ${header:CamelFileName} contents:\n ${bodyAs(String)}")
                .unmarshal(jacksonDataFormatForOrder)
                .marshal(avroDataFormat)
                .to("kafka:orders?brokers=localhost:19092,localhost:19093,localhost:19094");

    }
}
