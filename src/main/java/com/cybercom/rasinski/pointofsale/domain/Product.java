package com.cybercom.rasinski.pointofsale.domain;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private Barcode barcode;
    private BigDecimal price;

    public Product(Long id, String name, Barcode barcode, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
