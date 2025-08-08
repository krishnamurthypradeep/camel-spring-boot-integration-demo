package com.myapp.camel.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.camel.routes.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("orderItemConverter")
public class OrderItemConverter {

    @Autowired
    private ObjectMapper objectMapper;
   public OrderItem  mapToOrderItem(Map<String,Object> map){
        return objectMapper.convertValue(map, OrderItem.class);
    }
}
