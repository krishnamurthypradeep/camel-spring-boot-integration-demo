package com.myapp.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelSpringBootIntegrationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelSpringBootIntegrationDemoApplication.class, args);
    }

}

// data Conversion
// xml -> map to objects
// json -> objects to json
// activemq -> send the messages to activemq as json
// file to activemq


// Message Broker Kafka

// XML | JSON
// AVRO  data serialization format
// efficient storage
// big data , event streaming (kafka)
// Schema Based
// Compact
// Binary
// Polyglot
//


// JAXB
// VETRO
// transport protocol
// message format
// persistent store
// target system
// 1. It is validated
// 2. It is enriched
// 3. Transformed to desire format
// 4. Act upon
// Validate
// Enrich
// Transform
// Route
// Operate

