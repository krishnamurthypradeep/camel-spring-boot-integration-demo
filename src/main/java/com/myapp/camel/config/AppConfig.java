package com.myapp.camel.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean("activemq")
    ActiveMQConnectionFactory connectionFactory (){
     return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

}
