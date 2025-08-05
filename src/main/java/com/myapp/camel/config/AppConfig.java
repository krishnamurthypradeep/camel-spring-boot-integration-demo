package com.myapp.camel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.myapp.camel.avro.Order;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jackson.JacksonDataFormat;


import org.apache.camel.dataformat.avro.AvroDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean("activemq")
    ActiveMQConnectionFactory connectionFactory (){
     return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public JacksonDataFormat jacksonDataFormatForOrder() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JacksonDataFormat format = new JacksonDataFormat(Order.class);
        format.setObjectMapper(mapper);
        return format;
    }

    @Bean("avroOrderDataFormat")
    public AvroDataFormat avroOrderDataFormat() {
        return new AvroDataFormat(Order.SCHEMA$);
    }

}
