package com.myapp.camel.routes;

import java.io.Serializable;

public record OrderItem(String orderId, String productName, int quantity, double price) implements Serializable {
}
