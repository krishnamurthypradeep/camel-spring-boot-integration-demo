package com.myapp.camel.bean;

import com.myapp.camel.routes.OrderItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderLineItems implements Serializable {

    private String orderId;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Double totalPrice;

    public OrderLineItems() {
    }

    public OrderLineItems(String orderId) {
        this.orderId = orderId;
    }

    public void addItem(OrderItem item) {
        orderItems.add(item);
    }

    public Double getTotalPrice() {
        return orderItems.stream().mapToDouble(item -> item.price()*item.quantity()).sum();
    }

}
