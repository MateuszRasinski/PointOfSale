package com.cybercom.rasinski.pointofsale.domain;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;
    private Money totalSum;

    public ShoppingCart(List<Product> products) {
        this.products = products;
        this.totalSum = new Money(BigDecimal.ZERO);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Money getTotalSum() {
        return totalSum;
    }
}
