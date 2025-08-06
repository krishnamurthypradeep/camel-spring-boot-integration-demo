package com.myapp.camel.routes;

import com.myapp.camel.avro.Order;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.dataformat.avro.AvroDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class JsonToAvroToJmsRouterKafka extends RouteBuilder {
    //@Autowired
    private JacksonDataFormat jacksonDataFormatForOrder;

    @Override
    public void configure() throws Exception {
       // AvroDataFormat avroDataFormat = new AvroDataFormat(Order.SCHEMA$);

        from("file:data/avroorders?include=.*\\.json$&noop=true&autoCreate=false&directoryMustExist=true")
                .routeId("kafka-avro-json-route")

                .log("File ${header:CamelFileName} contents:\n ${bodyAs(String)}")

                .unmarshal(jacksonDataFormatForOrder)

               // .marshal(avroDataFormat)
                .to("kafka:order-avro"
                + "?keySerializer=org.apache.kafka.common.serialization.StringSerializer"
                + "&valueSerializer=io.confluent.kafka.serializers.KafkaAvroSerializer"
                + "&schemaRegistryURL=http://localhost:8081");



    }
}


// Primitive Types
// boolean
// int
// long
// float
// double
// bytes
// string


// record
// enum
// array
// map
// union ("null":"int")
// date -> int
// time-millis



// SimpleRegistry
// ApplicationContextRegistry
// OsgiServiceRegistry
// CdiBeanRegistry

// Data Formats provided with camel

// XML
// JSON
// CSV
// Bindy
// Avro -> Binary Format
// ProtoBuf
// Gzip
// JAXB
// Crypto
// HL7

