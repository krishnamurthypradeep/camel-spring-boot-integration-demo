package com.myapp.camel.routes;

import com.myapp.camel.avro.Order;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.dataformat.avro.AvroDataFormat;

import org.springframework.stereotype.Component;

@Component
public class JsonToAvroKafkaProducer extends RouteBuilder {

  //  @Autowired
    private JacksonDataFormat jacksonDataFormatForOrder;
    @Override
    public void configure() throws Exception {


        from("file:data/avroorders?include=.*\\.json$&noop=true&autoCreate=false&directoryMustExist=true")
                .routeId("json-avro-route")
                .log("File ${header:CamelFileName} contents:\n ${bodyAs(String)}")
                .unmarshal(jacksonDataFormatForOrder)
                .marshal("avroOrderDataFormat")
                .to("kafka:orders");

    }
}
