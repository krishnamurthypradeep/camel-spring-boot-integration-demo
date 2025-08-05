package com.myapp.camel.bean;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;

@XmlRootElement
public class Product {

    private String name;
    private BigDecimal price;
    private String description;
    private String brand;

    public Product() {
    }

    public Product(String name, BigDecimal price, String description, String brand) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
