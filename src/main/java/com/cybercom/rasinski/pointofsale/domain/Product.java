package com.cybercom.rasinski.pointofsale.domain;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;


    public Product(Long id, String name, BigDecimal price) {
        this.price = price;
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public BigDecimal getPrice() {
        return price;
    }
}
